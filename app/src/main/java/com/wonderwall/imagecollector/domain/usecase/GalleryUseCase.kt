package com.wonderwall.imagecollector.domain.usecase

import com.wonderwall.imagecollector.domain.model.ContentsItem
import com.wonderwall.imagecollector.domain.repository.GalleryRepository
import javax.inject.Inject

class GalleryUseCase @Inject constructor(
    private val repository: GalleryRepository
) {
    suspend fun save(item: ContentsItem) = repository.save(item)
    suspend fun getAll(): List<ContentsItem> = repository.getAll()
    suspend fun delete(item: ContentsItem) = repository.delete(item)
}