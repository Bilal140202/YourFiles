package com.yourfiles.manager.app

import android.net.Uri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.yourfiles.manager.app.Routes.Companion.FILE_DETAIL_VIEWER
import com.yourfiles.manager.app.Routes.Companion.FLAT_DUPLICATES_FILE_MANAGER
import com.yourfiles.manager.app.Routes.Companion.FLAT_IMAGES_FILE_MANAGER
import com.yourfiles.manager.app.Routes.Companion.FLAT_LARGE_FILE_MANAGER
import com.yourfiles.manager.app.Routes.Companion.FLAT_SCREENSHOTS_FILE_MANAGER
import com.yourfiles.manager.app.Routes.Companion.FLAT_VIDEOS_FILE_MANAGER
import com.yourfiles.manager.app.Routes.Companion.FLAT_WHATSAPP_FILE_MANAGER
import com.yourfiles.manager.app.Routes.Companion.HOME
import com.yourfiles.manager.app.Routes.Companion.OPTIMISE_IMAGES
import com.yourfiles.manager.app.Routes.Companion.SETTINGS
import com.yourfiles.manager.app.Routes.Companion.TRASH
import com.yourfiles.manager.presentation.ui.pages.HomeComposable
import com.yourfiles.manager.presentation.ui.pages.FileDetailViewerCompose
import com.yourfiles.manager.presentation.ui.pages.FlatFileManager
import com.yourfiles.manager.presentation.ui.pages.FlatImagesFileManager
import com.yourfiles.manager.presentation.ui.pages.FlatLargeFilesManager
import com.yourfiles.manager.presentation.ui.pages.FlatScreenshotsFileManager
import com.yourfiles.manager.presentation.ui.pages.FlatVideosFileManager
import com.yourfiles.manager.presentation.ui.pages.ImageOptimiserPage
import com.yourfiles.manager.presentation.ui.pages.SettingsPage
import com.yourfiles.manager.presentation.ui.pages.TrashPage
import com.yourfiles.manager.presentation.ui.pages.WhatsAppCleanerPage
val router: NavGraphBuilder.() -> Unit = {
    composable(OPTIMISE_IMAGES) {
        ImageOptimiserPage()
    }
    composable(FLAT_DUPLICATES_FILE_MANAGER) {
        FlatFileManager()
    }
    composable(FLAT_VIDEOS_FILE_MANAGER) {
        FlatVideosFileManager()
    }
    composable(FLAT_IMAGES_FILE_MANAGER) {
        FlatImagesFileManager()
    }
    composable(HOME) {
        HomeComposable()
    }
    composable(route = FLAT_LARGE_FILE_MANAGER) {
        FlatLargeFilesManager()
    }
    composable(FLAT_SCREENSHOTS_FILE_MANAGER) {
        FlatScreenshotsFileManager()
    }
    composable(FLAT_WHATSAPP_FILE_MANAGER) {
        WhatsAppCleanerPage()
    }
    composable(TRASH) {
        TrashPage()
    }
    composable(SETTINGS) {
        SettingsPage()
    }
    composable(
        "$FILE_DETAIL_VIEWER?url={url}&category={category}&md5={md5}", arguments = listOf(
            navArgument("url") { type = NavType.StringType },
            navArgument("category") { type = NavType.StringType },
            navArgument("md5") { type = NavType.StringType; nullable = true; defaultValue = null },
        )
    ) { backStackEntry ->
        val url = backStackEntry.arguments?.getString("url")!!
        val category = backStackEntry.arguments?.getString("category") ?: ""
        val md5 = backStackEntry.arguments?.getString("md5")
        val resolvedUrl = Uri.decode(url)
        FileDetailViewerCompose(resolvedUrl, category = category, md5 = md5)
    }
}


interface Routes {
    companion object {
        const val FLAT_DUPLICATES_FILE_MANAGER = "/flat-duplicates-file-manager"
        const val FLAT_IMAGES_FILE_MANAGER = "/flat-images-file-manager"
        const val FLAT_VIDEOS_FILE_MANAGER = "/flat-videos-file-manager"
        const val FLAT_LARGE_FILE_MANAGER = "/flat-large-file-manager"
        const val FLAT_SCREENSHOTS_FILE_MANAGER = "/flat-screenshots-file-manager"
        const val FLAT_WHATSAPP_FILE_MANAGER = "/flat-whatsapp-file-manager"
        const val HOME = "/home"
        const val ONBOARDING = "/onboarding"
        const val OPTIMISE_IMAGES = "/optimise-images"
        const val FILE_DETAIL_VIEWER = "/file-detail-viewer"
        const val TRASH = "/trash"
        const val SETTINGS = "/settings"
    }
}
