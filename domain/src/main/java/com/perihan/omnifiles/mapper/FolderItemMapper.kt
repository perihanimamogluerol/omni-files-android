package com.perihan.omnifiles.mapper

import com.perihan.omnifiles.data.FolderItem
import com.perihan.omnifiles.model.FolderItemDomainModel

fun FolderItem.toDomain(): FolderItemDomainModel {
    return FolderItemDomainModel(
        id = id, uriString = uriString, displayName = displayName
    )
}

fun FolderItemDomainModel.toData(): FolderItem {
    return FolderItem(
        id = id, uriString = uriString, displayName = displayName
    )
}