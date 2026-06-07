package com.yourfiles.manager.presentation.vm

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourfiles.manager.app.App
import com.yourfiles.manager.data.model.LocalFile
import com.yourfiles.manager.data.repository.LocalFilesRepoImpl
import com.yourfiles.manager.domain.interactors.BestFileSelector
import com.yourfiles.manager.domain.interactors.FileUseCases
import com.yourfiles.manager.utils.SavedMemoryTracker
import com.yourfiles.manager.utils.TrashManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import java.io.File

class FlatDuplicatesFileManagerVM : ViewModel() {

    val selectedFileIds = mutableStateOf(setOf<String>())
    val uncheckedFiles = mutableSetOf<String>()
    val showDeleteDialog = mutableStateOf(false)
    val isDeleting = mutableStateOf(false)
    val autoSelectResult = mutableStateOf<String?>(null)
    val showUndoSnackbar = mutableStateOf(false)
    val lastTrashedEntries = mutableStateOf<Map<String, String>>(emptyMap())

    private val fileUseCases =
        FileUseCases(LocalFilesRepoImpl(App.instance.db.localFilesDao()))

    val duplicateFiles: StateFlow<Map<String, List<LocalFile>>?> =
        fileUseCases.getMediaFiles()
            .map { files ->
                files
                    .filter { it.md5CheckSum != null }
                    .groupBy { it.md5CheckSum!! }
                    .filter { it.value.size > 1 }
            }
            .flowOn(Dispatchers.Default)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun selectDuplicateFiles() {
        viewModelScope.launch {
            val duplicateGroups = duplicateFiles.filterNotNull().first()
            val allFilesExceptFirst = duplicateGroups.values
                .flatMap { it.drop(1) }
                .map { it.id }
                .filter { it !in uncheckedFiles }
                .toSet()
            selectedFileIds.value = allFilesExceptFirst
        }
    }

    /**
     * Auto-Select Best: Score files by quality/location and keep only the best,
     * selecting all others for deletion.
     */
    fun autoSelectBest() {
        viewModelScope.launch {
            val groups = duplicateFiles.value ?: return@launch
            val toDeleteMap = BestFileSelector.selectBestToDelete(groups)

            val allDeleteIds = toDeleteMap.values.flatten().toSet()
            val originalsCount = groups.values.count { it.size > 1 }

            selectedFileIds.value = allDeleteIds
            autoSelectResult.value = "Keeping $originalsCount originals, selected ${allDeleteIds.size} duplicates to clean"
        }
    }

    fun toggleGroupSelection(groupFiles: List<LocalFile>) {
        if (groupFiles.size <= 1) return
        val filesExceptFirst = groupFiles.drop(1)
        val ids = filesExceptFirst.map { it.id }.toSet()
        val allExceptFirstSelected = ids.all { selectedFileIds.value.contains(it) }
        if (allExceptFirstSelected) {
            selectedFileIds.value -= ids
            uncheckedFiles.addAll(ids)
        } else {
            uncheckedFiles.removeAll(ids)
            selectedFileIds.value += ids
        }
    }

    fun areAllExceptFirstSelected(groupFiles: List<LocalFile>): Boolean {
        if (groupFiles.size <= 1) return false

        val filesExceptFirst = groupFiles.drop(1)
        val currentSelection = selectedFileIds.value

        return filesExceptFirst.all { file ->
            currentSelection.contains(file.id)
        }
    }

    fun toggleAllGroups() {
        val duplicateGroups = duplicateFiles.value ?: return
        val currentSelection = selectedFileIds.value
        val allGroupsSelected = duplicateGroups.values.all { group ->
            if (group.size <= 1) return@all true
            group.drop(1).all { file -> currentSelection.contains(file.id) }
        }
        val allIds = duplicateGroups.values
            .filter { it.size > 1 }
            .flatMap { it.drop(1).map { f -> f.id } }
            .toSet()
        if (allGroupsSelected) {
            selectedFileIds.value -= allIds
            uncheckedFiles.addAll(allIds)
        } else {
            uncheckedFiles.removeAll(allIds)
            selectedFileIds.value += allIds
        }
    }


    fun deleteFile(localFile: LocalFile) {
        viewModelScope.launch(Dispatchers.IO) {
            val sizeBytes = File(localFile.id).length()
            launch { fileUseCases.deleteFile(localFile.id) }.join()
            File(localFile.id).apply { if (exists()) delete() }
            SavedMemoryTracker.addSavedBytes(sizeBytes)
            withContext(Dispatchers.Main) {
                selectedFileIds.value -= localFile.id
                uncheckedFiles.remove(localFile.id)
            }
        }
    }

    fun deleteFiles(ids: Set<String>) {
        showDeleteDialog.value = true
        pendingDeleteFiles = ids
    }

    fun confirmDeleteFiles() {
        viewModelScope.launch(Dispatchers.IO) {
            val totalBytes = pendingDeleteFiles.sumOf { File(it).length() }
            withContext(Dispatchers.Main) { isDeleting.value = true }

            // Move to trash instead of permanent delete
            val trashedEntries = TrashManager.moveToTrash(pendingDeleteFiles)

            // Remove from database
            launch {
                fileUseCases.deleteFiles(pendingDeleteFiles.toList())
            }.join()

            SavedMemoryTracker.addSavedBytes(totalBytes)

            // Wait for Room to emit updated list
            val snapshotBeforeDelete = duplicateFiles.value
            val updatedGroups = withTimeoutOrNull(3000) {
                duplicateFiles.first { it != snapshotBeforeDelete }
            }
            val validIds = updatedGroups?.values?.flatten()?.map { it.id }?.toSet() ?: emptySet()

            withContext(Dispatchers.Main) {
                selectedFileIds.value = selectedFileIds.value.intersect(validIds)
                uncheckedFiles.removeAll(pendingDeleteFiles)
                showDeleteDialog.value = false
                pendingDeleteFiles = emptySet()
                isDeleting.value = false

                // Show undo snackbar
                if (trashedEntries.isNotEmpty()) {
                    lastTrashedEntries.value = trashedEntries
                    showUndoSnackbar.value = true
                }
            }
        }
    }

    fun undoDelete() {
        showUndoSnackbar.value = false
        viewModelScope.launch(Dispatchers.IO) {
            val entries = lastTrashedEntries.value
            TrashManager.undoTrash(entries)
            // Re-add files to database by triggering a rescan
            lastTrashedEntries.value = emptyMap()
        }
    }

    fun cancelDelete() {
        showDeleteDialog.value = false
        pendingDeleteFiles = emptySet()
    }

    private var pendingDeleteFiles = emptySet<String>()

}
