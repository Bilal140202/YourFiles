package com.yourfiles.manager.data.repository

import androidx.room.withTransaction
import androidx.sqlite.db.SimpleSQLiteQuery
import com.yourfiles.manager.app.App
import com.yourfiles.manager.data.db.dao.LocalFilesDao
import com.yourfiles.manager.data.model.LocalFile
import com.yourfiles.manager.domain.repository.LocalFilesRepo
import kotlinx.coroutines.flow.Flow

class LocalFilesRepoImpl(private val dao: LocalFilesDao) : LocalFilesRepo {

    override suspend fun insertFile(localFile: LocalFile) {
        return dao.insert(localFile)
    }

    override suspend fun insertAll(localFiles: List<LocalFile>) {
        return dao.insertAll(localFiles)
    }

    override fun getAllFiles(): Flow<List<LocalFile>> {
        return dao.getAll()
    }

    override suspend fun deleteFile(id: String) {
        return dao.delete(id)
    }

    override suspend fun deleteFiles(ids: List<String>) {
        App.instance.db.withTransaction {
            ids.forEach { dao.delete(it) }
        }
    }

    override fun getFilesViaQuery(query: String): Flow<List<LocalFile>> {
        return dao.getFilesViaQuery(SimpleSQLiteQuery(query))
    }

    override fun fileAlreadyExists(md5: String): Boolean {
        return dao.fileExists(md5)
    }

    override suspend fun getFileById(id: String): LocalFile? {
        return dao.get(id)
    }

    override fun getDuplicateMediaFiles(): Flow<List<LocalFile>> {
        return dao.getDuplicateMediaFiles()
    }

    override fun getDuplicateCopies(): Flow<List<LocalFile>> {
        return dao.getDuplicateCopies()
    }

    override fun getFilesSizeSumViaQuery(query: String): Flow<Long> {
        return dao.getSumViaQuery(SimpleSQLiteQuery(query))
    }

    override suspend fun applyOptimisationResults(results: List<Pair<String, Long>>) {
        App.instance.db.withTransaction {
            results.forEach { (id, newSizeKb) ->
                dao.updateSizeAndMarkAsOptimised(id, newSizeKb)
            }
        }
    }
}