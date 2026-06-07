package com.yourfiles.manager.utils

import android.content.Context
import com.yourfiles.manager.app.App
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Holds metadata about the current trash contents.
 */
data class TrashInfo(val fileCount: Int, val totalSize: Long)

/**
 * Singleton utility for safely moving deleted files to a trash folder instead of
 * permanently deleting them. Files are stored under the app's external files directory
 * so they survive across app restarts but are cleaned up when the app is uninstalled.
 *
 * Trash location: /Android/data/com.yourfiles.manager/files/Trash/
 */
object TrashManager {

    private const val TRASH_DIR_NAME = "Trash"
    private const val TRASH_RETENTION_DAYS = 30L

    /** In-memory mapping of originalPath -> trashPath for the most recent trash operation. */
    private val lastTrashedEntries = mutableMapOf<String, String>()

    private val _trashInfo = MutableStateFlow(TrashInfo(fileCount = 0, totalSize = 0L))

    /** Flow that emits the current trash state (count + size) whenever it changes. */
    val trashInfo: StateFlow<TrashInfo> = _trashInfo.asStateFlow()

    // ────────────────────────────────────────────────────────────────────────────
    // Private helpers
    // ────────────────────────────────────────────────────────────────────────────

    /**
     * Returns the trash directory, creating it if it does not yet exist.
     *
     * @return the [File] representing the trash directory, or **null** if the
     *         directory could not be created (e.g. external storage unavailable).
     */
    private fun getTrashDir(context: Context): File? {
        val baseDir = context.getExternalFilesDir(null) ?: return null
        val trashDir = File(baseDir, TRASH_DIR_NAME)
        if (!trashDir.exists()) {
            val created = trashDir.mkdirs()
            if (!created) return null
        }
        return trashDir
    }

    /**
     * Generates a unique filename inside trash to avoid collisions.
     * Format: `<timestamp_ms>_<originalFileName>`
     */
    private fun uniqueTrashName(file: File): String {
        return "${System.currentTimeMillis()}_${file.name}"
    }

    /**
     * Refreshes the [_trashInfo] state flow with current trash contents.
     * Must be called from a context where `App.instance` is available.
     */
    private fun refreshTrashInfo() {
        val context = App.instance
        val files = getTrashFiles(context)
        val count = files.size
        val size = files.sumOf { it.length() }
        _trashInfo.value = TrashInfo(fileCount = count, totalSize = size)
    }

    // ────────────────────────────────────────────────────────────────────────────
    // Public API
    // ────────────────────────────────────────────────────────────────────────────

    /**
     * Move files to trash.
     *
     * For each file the method creates a uniquely-named copy inside the trash
     * directory using [File.renameTo]. If a file does not exist or cannot be
     * moved it is silently skipped.
     *
     * @param filePaths Set of absolute file paths to move to trash.
     * @return Map of original path -> trash path for undo support (only contains
     *         successfully moved entries).
     */
    suspend fun moveToTrash(filePaths: Set<String>): Map<String, String> {
        val context = App.instance
        val trashDir = getTrashDir(context) ?: return emptyMap()

        val result = mutableMapOf<String, String>()

        for (filePath in filePaths) {
            val source = File(filePath)
            if (!source.exists()) continue

            val trashFile = File(trashDir, uniqueTrashName(source))
            val moved = source.renameTo(trashFile)

            if (moved) {
                result[filePath] = trashFile.absolutePath
            }
        }

        // Update in-memory undo map with only this batch
        lastTrashedEntries.clear()
        lastTrashedEntries.putAll(result)

        refreshTrashInfo()
        return result
    }

    /**
     * Undo the last trash operation — move files back to their original locations.
     *
     * @param trashEntries Map of original path -> trash path (typically obtained from
     *                     a previous [moveToTrash] call).
     * @return the number of files that were successfully restored.
     */
    suspend fun undoTrash(trashEntries: Map<String, String>): Int {
        var restoredCount = 0

        for ((originalPath, trashPath) in trashEntries) {
            val trashFile = File(trashPath)
            val originalFile = File(originalPath)

            if (!trashFile.exists()) continue

            // Make sure the parent directory of the original location still exists
            val parentDir = originalFile.parentFile
            if (parentDir != null && !parentDir.exists()) {
                val dirsCreated = parentDir.mkdirs()
                if (!dirsCreated) continue
            }

            val restored = trashFile.renameTo(originalFile)
            if (restored) {
                restoredCount++
                lastTrashedEntries.remove(originalPath)
            }
        }

        refreshTrashInfo()
        return restoredCount
    }

    /**
     * Delete files that have been in the trash for longer than [TRASH_RETENTION_DAYS]
     * days (30 days).
     *
     * @return the number of files that were permanently deleted.
     */
    suspend fun cleanupOldTrash(context: Context): Int {
        val trashDir = getTrashDir(context) ?: return 0

        val cutoffMillis = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(TRASH_RETENTION_DAYS)
        val trashFiles = trashDir.listFiles()
        var deletedCount = 0

        if (trashFiles != null) {
            for (file in trashFiles) {
                if (file.lastModified() < cutoffMillis) {
                    val deleted = file.delete()
                    if (deleted) {
                        deletedCount++
                        // Also remove from undo map if present
                        lastTrashedEntries.values.remove(file.absolutePath)
                    }
                }
            }
        }

        refreshTrashInfo()
        return deletedCount
    }

    /**
     * List all files currently residing in the trash directory.
     *
     * @return a list of [File] objects, or an empty list if the trash directory
     *         cannot be accessed.
     */
    fun getTrashFiles(context: Context): List<File> {
        val trashDir = getTrashDir(context) ?: return emptyList()
        val files = trashDir.listFiles()
        return files?.toList() ?: emptyList()
    }

    /**
     * Calculate the total size (in bytes) of all files currently in the trash.
     */
    fun getTrashSize(context: Context): Long {
        return getTrashFiles(context).sumOf { it.length() }
    }

    /**
     * Permanently delete every file inside the trash directory.
     *
     * @return the number of files deleted.
     */
    suspend fun emptyTrash(context: Context): Int {
        val trashDir = getTrashDir(context) ?: return 0
        val trashFiles = trashDir.listFiles()
        var deletedCount = 0

        if (trashFiles != null) {
            for (file in trashFiles) {
                val deleted = file.delete()
                if (deleted) {
                    deletedCount++
                    lastTrashedEntries.values.remove(file.absolutePath)
                }
            }
        }

        lastTrashedEntries.clear()
        refreshTrashInfo()
        return deletedCount
    }
}
