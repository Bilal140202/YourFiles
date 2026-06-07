package com.yourfiles.manager.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yourfiles.manager.data.db.dao.LocalFilesDao
import com.yourfiles.manager.data.model.LocalFile

@Database(entities = [LocalFile::class], version = 6, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun localFilesDao(): LocalFilesDao
}


