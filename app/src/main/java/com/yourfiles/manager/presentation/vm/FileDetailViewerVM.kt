package com.yourfiles.manager.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourfiles.manager.app.App
import com.yourfiles.manager.data.model.LocalFile
import com.yourfiles.manager.data.repository.LocalFilesRepoImpl
import com.yourfiles.manager.domain.interactors.FileUseCases
import com.yourfiles.manager.utils.SavedMemoryTracker
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class FileDetailViewerVM : ViewModel() {
    private val fileUseCases = FileUseCases(LocalFilesRepoImpl(App.instance.db.localFilesDao()))

    private val _categoryFiles = MutableStateFlow<List<LocalFile>?>(null)
    val categoryFiles: StateFlow<List<LocalFile>?> = _categoryFiles

    val showDeleteDialog = mutableStateOf(false)
    val isDeleting = mutableStateOf(false)

    private var filesJob: Job? = null

    fun loadFilesByCategory(category: String) {
        filesJob?.cancel()
        filesJob = viewModelScope.launch {
            fileUseCases.getFilesByCategory(category)
                .flowOn(Dispatchers.IO)
                .collect { _categoryFiles.value = it }
        }
    }

    fun loadFilesByMd5(md5: String) {
        filesJob?.cancel()
        filesJob = viewModelScope.launch {
            fileUseCases.getFilesByMd5(md5)
                .flowOn(Dispatchers.IO)
                .collect { _categoryFiles.value = it }
        }
    }

    fun requestDelete() {
        showDeleteDialog.value = true
    }

    fun cancelDelete() {
        showDeleteDialog.value = false
    }

    fun confirmDelete(fileId: String, onDeleted: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) { isDeleting.value = true }
            val sizeBytes = File(fileId).length()
            fileUseCases.deleteFile(fileId)
            File(fileId).apply { if (exists()) delete() }
            SavedMemoryTracker.addSavedBytes(sizeBytes)
            withContext(Dispatchers.Main) {
                isDeleting.value = false
                showDeleteDialog.value = false
                onDeleted()
            }
        }
    }


    fun getFileInfo(file: LocalFile): String {
        return fileUseCases.getFileInfo(file.id)
    }

}