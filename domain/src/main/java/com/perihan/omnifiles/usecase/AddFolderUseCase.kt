package com.perihan.omnifiles.usecase

import com.perihan.omnifiles.data.FolderItem
import com.perihan.omnifiles.repository.FolderRepository
import javax.inject.Inject

class AddFolderUseCase @Inject constructor(
    private val repository: FolderRepository
) {
    suspend operator fun invoke(folder: FolderItem) {
        repository.addFolder(folder)
    }
}