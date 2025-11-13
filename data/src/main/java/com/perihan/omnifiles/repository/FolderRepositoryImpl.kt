package com.perihan.omnifiles.repository

import com.perihan.omnifiles.data.FolderItem
import com.perihan.omnifiles.local.FolderLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FolderRepositoryImpl @Inject constructor(
    private val localDataSource: FolderLocalDataSource,
) : FolderRepository {

    override fun fetchFolders(): Flow<List<FolderItem>> = localDataSource.fetchFolders()

    override suspend fun addFolder(folder: FolderItem) {
        val current = localDataSource.readFolders()

        if (current.any { it.uriString == folder.uriString }) {
            return
        }

        val updated = current + folder
        localDataSource.writeFolders(updated)
    }

    override suspend fun removeFolder(folderId: String) {
        val current = localDataSource.readFolders()
        val updated = current.filterNot { it.id == folderId }
        localDataSource.writeFolders(updated)
    }
}