package com.yourfiles.manager.domain.interactors

import com.yourfiles.manager.data.model.LocalFile

/**
 * Auto-selects the best file to keep when deleting duplicates.
 *
 * Scoring prioritises files that appear to be originals:
 * - Stored in camera / default folders (DCIM, Camera, Downloads, Pictures)
 * - Older (likely the original, not a copy or cached version)
 * - Larger (likely higher quality / resolution)
 *
 * Tiebreaker: shortest file path (more standard / root location).
 */
object BestFileSelector {

    /**
     * For each group of duplicates (files sharing the same hash), score every file
     * and mark all **except** the highest-scoring one for deletion.
     *
     * @param duplicateGroups map of hash → list of duplicate files
     * @return map of hash → list of file IDs (absolute paths) to DELETE
     */
    fun selectBestToDelete(duplicateGroups: Map<String, List<LocalFile>>): Map<String, List<String>> {
        return duplicateGroups
            .filter { it.value.size > 1 }
            .mapValues { (_, files) ->
                val best = findBest(files)
                files.filterNot { it.id == best.id }.map { it.id }
            }
    }

    /**
     * Returns the best (highest-scoring) file from a group of duplicates.
     *
     * @param files non-empty list of duplicate files
     * @throws IllegalArgumentException if [files] is empty
     */
    fun findBest(files: List<LocalFile>): LocalFile {
        require(files.isNotEmpty()) { "Cannot find best file from an empty list" }
        if (files.size == 1) return files.first()

        val maxGroupSize = files.maxOf { it.size }
        val oldestTime = files.mapNotNull { it.modifiedTime }.minOrNull()

        return files.maxWith(
            compareBy(
                { scoreFile(it, maxGroupSize, oldestTime) },
                // Tiebreaker: prefer shorter path (more standard / root location).
                // Negated so that maxWith picks the smallest length.
                { -it.id.length }
            )
        )
    }

    /**
     * Score a single file. Higher score = better (should be KEPT).
     *
     * Scoring rules:
     * - **Location bonus:** DCIM/Camera +10, Downloads +8, Pictures/Photos +5, else +0
     * - **Date bonus:**     oldest modified-time in group +5 (likely the original)
     * - **Size bonus:**     largest file in group +3 (likely best quality)
     *
     * @param file           the file to score
     * @param maxGroupSize   the largest file size in the duplicate group
     * @param oldestTime     the oldest [LocalFile.modifiedTime] in the group
     */
    private fun scoreFile(
        file: LocalFile,
        maxGroupSize: Long = file.size,
        oldestTime: Long? = null,
    ): Int {
        var score = 0

        // --- Location bonus (case-insensitive path matching) ---
        val path = file.id.lowercase()
        score += when {
            "dcim/camera" in path -> 10
            "dcim" in path       -> 10
            "download" in path   -> 8
            "pictures" in path || "photos" in path -> 5
            // WhatsApp, social-media caches, tmp folders → 0 (lowest priority)
            else -> 0
        }

        // --- Date bonus: the oldest file is likely the original ---
        if (oldestTime != null && file.modifiedTime == oldestTime) {
            score += 5
        }

        // --- Size bonus: the largest file likely has the best quality / resolution ---
        if (maxGroupSize > 0 && file.size == maxGroupSize) {
            score += 3
        }

        return score
    }
}
