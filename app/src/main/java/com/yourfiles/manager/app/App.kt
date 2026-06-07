package com.yourfiles.manager.app

import android.app.Application
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.room.Room
import coil3.ImageLoader
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.video.VideoFrameDecoder
import com.yourfiles.manager.data.db.AppDatabase
import com.yourfiles.manager.utils.SavedMemoryTracker
import com.yourfiles.manager.utils.TrashManager
import android.util.Log


class App : Application() {

    private lateinit var navController: NavHostController

    lateinit var imageLoader: ImageLoader

    lateinit var db: AppDatabase


    fun navController(): NavHostController {
        return instance.navController
    }

    fun initNavController(navController: NavHostController) {
        this.navController = navController
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initDB()
        initLibraries()
        SavedMemoryTracker.initialize()
        // Clean up old trash on app start
        TrashManager.cleanupOldTrash(applicationContext)
    }

    private fun initDB() {
        db = Room.databaseBuilder(
            instance.applicationContext, AppDatabase::class.java, "yourfiles-database"
        ).fallbackToDestructiveMigration(true)
            .build()
    }

    private fun initLibraries() {
        imageLoader = ImageLoader.Builder(instance).memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED).components {
                add(VideoFrameDecoder.Factory())
            }.crossfade(true).build()
    }

    fun log(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message)
        }
    }

    companion object {
        lateinit var instance: App
            private set
    }
}


@ExperimentalFoundationApi
@Composable
fun YourFilesApp(
    modifier: Modifier = Modifier,
    startDestination: String = Routes.HOME,
) {
    AppTheme {
        NavHost(
            modifier = modifier,
            navController = App.instance.navController(),
            startDestination = startDestination,
            builder = router
        )
    }
}
