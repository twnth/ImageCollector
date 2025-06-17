package com.wonderwall.imagecollector.domain.repository

import com.wonderwall.imagecollector.domain.model.ContentsItem

interface GalleryRepository {
    suspend fun save(item: ContentsItem)
    suspend fun getAll(): List<ContentsItem>
    suspend fun delete(item: ContentsItem)
}