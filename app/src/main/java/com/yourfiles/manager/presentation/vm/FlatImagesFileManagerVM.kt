package com.yourfiles.manager.presentation.vm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourfiles.manager.app.App
import com.yourfiles.manager.data.repository.LocalFilesRepoImpl
import com.yourfiles.manager.domain.interactors.FileUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class FlatImagesFileManagerVM : SelectableDeletableVM() {
    fun getImageFiles() = fileUseCases.getLargeImageFiles()

}