package com.perihan.omnifiles.usecase

import com.perihan.omnifiles.mapper.toDomain
import com.perihan.omnifiles.model.FolderItemDomainModel
import com.perihan.omnifiles.repository.FolderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchFoldersUseCase @Inject constructor(
    private val repository: FolderRepository
) {
    operator fun invoke(): Flow<List<FolderItemDomainModel>> =
        repository.fetchFolders().map { it.map { item -> item.toDomain() } }
}