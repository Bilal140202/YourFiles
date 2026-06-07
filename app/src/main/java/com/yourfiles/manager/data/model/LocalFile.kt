package com.yourfiles.manager.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yourfiles.manager.utils.getMimeType
import com.yourfiles.manager.utils.readIsOptimised
import com.yourfiles.manager.utils.size
import java.io.File

@Entity
open class LocalFile(
    @ColumnInfo(name = "mimeType") val fileType: String?,
    @ColumnInfo(name = "modifiedTime") val modifiedTime: Long?,
    @ColumnInfo(name = "originalFilename") val fileName: String?,
    @ColumnInfo(name = "size") val size: Long,
    @ColumnInfo(name = "md5", index = true) val md5CheckSum: String?,
    @PrimaryKey val id: String,
    @ColumnInfo(name = "isOptimised", defaultValue = "0") val isOptimised: Boolean = false,
)

fun File.toLocalFile(md5: String?): LocalFile {
    return LocalFile(
        getMimeType(absolutePath), lastModified(), name, size(), md5, absolutePath,
        isOptimised = readIsOptimised()
    )
}