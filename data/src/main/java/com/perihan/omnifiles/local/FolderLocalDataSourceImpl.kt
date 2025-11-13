package com.perihan.omnifiles.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.perihan.omnifiles.data.FolderItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FolderLocalDataSourceImpl @Inject constructor(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher,
) : FolderLocalDataSource {

    companion object {
        private const val PREFS_NAME = "omni_files_prefs"
        private const val KEY_FOLDER_URIS = "folder_uris"
    }

    private val prefs: SharedPreferences
        get() = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override suspend fun readFolders(): List<FolderItem> = withContext(ioDispatcher) {
        val set = prefs.getStringSet(KEY_FOLDER_URIS, emptySet()) ?: emptySet()
        set.mapNotNull { entry ->
            val parts = entry.split("|", limit = 2)
            val uriString = parts.getOrNull(0) ?: return@mapNotNull null
            val name = parts.getOrNull(1)
            FolderItem(
                id = uriString, uriString = uriString, displayName = name
            )
        }
    }

    override suspend fun writeFolders(folders: List<FolderItem>) = withContext(ioDispatcher) {
        val set = folders.map { folder ->
            "${folder.uriString}|${folder.displayName.orEmpty()}"
        }.toSet()

        prefs.edit {
            putStringSet(KEY_FOLDER_URIS, set)
        }
    }

    override fun fetchFolders(): Flow<List<FolderItem>> = flow {
        emit(readFolders())
    }.flowOn(ioDispatcher)
}