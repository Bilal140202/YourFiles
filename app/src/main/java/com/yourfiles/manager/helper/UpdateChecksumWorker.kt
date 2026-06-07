package com.yourfiles.manager.helper

import android.content.Context
import androidx.room.withTransaction
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.yourfiles.manager.app.App
import com.yourfiles.manager.utils.md5
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.coroutineScope
import android.util.Log
import java.io.File

class UpdateChecksumWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    companion object {
        private const val BATCH_SIZE = 50
    }

    override suspend fun doWork(): Result {
        val dao = App.instance.db.localFilesDao()

        while (true) {
            Log.d("YourFiles", "Processing batch files.")

            val filesToUpdate = dao.getFilesWithoutChecksum(BATCH_SIZE)
            Log.d("YourFiles", "Found ${filesToUpdate.size} files to process.")

            if (filesToUpdate.isEmpty()) {
                break // No more files to process
            }

            Log.d("YourFiles", "Processing a batch of ${filesToUpdate.size} files.")

            try {
                coroutineScope {
                    val updates = filesToUpdate.map { localFile ->
                        async(Dispatchers.IO) {
                            Pair(localFile.id, File(localFile.id).md5() ?: "")
                        }
                    }.awaitAll()

                    App.instance.db.withTransaction {
                        updates.forEach { (id, md5) -> dao.updateMd5(id, md5) }
                    }
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e("YourFiles", e, "Error processing batch.")
                return Result.retry()
            }
        }

        return Result.success()
    }
}