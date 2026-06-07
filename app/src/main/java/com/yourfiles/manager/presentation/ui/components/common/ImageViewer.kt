package com.yourfiles.manager.presentation.ui.components.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import coil3.compose.rememberAsyncImagePainter
import com.yourfiles.manager.R
import com.yourfiles.manager.app.App

@Composable
fun ImageViewer(imagePath: String) {
    val painter = rememberAsyncImagePainter(
        model = imagePath,
        imageLoader = App.instance.imageLoader,
        error = painterResource(id = R.drawable.ic_file)
    )
    androidx.compose.foundation.Image(
        painter = painter, contentDescription = "", modifier = Modifier.fillMaxSize()
    )
}
