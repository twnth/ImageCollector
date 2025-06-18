package com.wonderwall.imagecollector.data.db.gallery

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gallery_list")
data class GalleryEntity(
    @PrimaryKey val idx: Int,
    val thumbnailUrl: String,
    val dateTime: String,
    val type: String,
    val regDate: Long?,
)