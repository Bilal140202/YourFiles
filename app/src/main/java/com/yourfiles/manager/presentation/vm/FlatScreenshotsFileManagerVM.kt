package com.yourfiles.manager.presentation.vm

class FlatScreenshotsFileManagerVM : SelectableDeletableVM() {
    fun getScreenshotFiles() = fileUseCases.getScreenshotFiles()
}
