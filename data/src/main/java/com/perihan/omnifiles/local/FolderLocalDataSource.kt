package com.perihan.omnifiles.local

import com.perihan.omnifiles.data.FolderItem
import kotlinx.coroutines.flow.Flow

interface FolderLocalDataSource {
    suspend fun readFolders(): List<FolderItem>
    suspend fun writeFolders(folders: List<FolderItem>)
    fun fetchFolders(): Flow<List<FolderItem>>
}