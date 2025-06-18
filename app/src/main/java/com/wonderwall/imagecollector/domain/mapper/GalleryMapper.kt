package com.wonderwall.imagecollector.domain.mapper

import com.wonderwall.imagecollector.data.db.gallery.GalleryEntity
import com.wonderwall.imagecollector.domain.model.ContentsItem
import com.wonderwall.imagecollector.domain.model.ContentsType

fun ContentsItem.toEntity(): GalleryEntity =
    GalleryEntity(idx, thumbnailUrl.toString(), dateTime, type.name, regDate)

fun GalleryEntity.toDomain(): ContentsItem =
    ContentsItem(
        idx,
        thumbnailUrl,
        dateTime,
        isFavorite = false,
        ContentsType.valueOf(type),
        regDate
    )