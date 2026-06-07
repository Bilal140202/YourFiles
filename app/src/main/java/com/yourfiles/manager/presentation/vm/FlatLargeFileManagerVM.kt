package com.yourfiles.manager.presentation.vm


class FlatLargeFileManagerVM : SelectableDeletableVM() {
    fun getLargeFiles() = fileUseCases.getLargeFiles()
}