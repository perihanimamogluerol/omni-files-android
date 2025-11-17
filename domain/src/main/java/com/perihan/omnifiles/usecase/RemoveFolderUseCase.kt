package com.perihan.omnifiles.usecase

import com.perihan.omnifiles.repository.FolderRepository
import javax.inject.Inject

class RemoveFolderUseCase @Inject constructor(
    private val repository: FolderRepository
) {
    suspend operator fun invoke(folderId: String) {
        repository.removeFolder(folderId)
    }
}