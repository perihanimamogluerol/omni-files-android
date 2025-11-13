package com.perihan.omnifiles.repository

import com.perihan.omnifiles.data.FolderItem
import kotlinx.coroutines.flow.Flow

interface FolderRepository {
    fun fetchFolders(): Flow<List<FolderItem>>
    suspend fun addFolder(folder: FolderItem)
    suspend fun removeFolder(folderId: String)
}