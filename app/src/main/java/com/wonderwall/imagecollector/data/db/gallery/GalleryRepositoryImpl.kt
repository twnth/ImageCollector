package com.wonderwall.imagecollector.data.db.gallery

import com.wonderwall.imagecollector.domain.mapper.toDomain
import com.wonderwall.imagecollector.domain.mapper.toEntity
import com.wonderwall.imagecollector.domain.model.ContentsItem
import com.wonderwall.imagecollector.domain.repository.GalleryRepository
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    private val dao: GalleryDao
): GalleryRepository {
    override suspend fun save(item: ContentsItem) {
        dao.insert(item.toEntity())
    }

    override suspend fun getAll(): List<ContentsItem> {
        return dao.getAll().map { it.toDomain() }
    }

    override suspend fun delete(item: ContentsItem) {
        dao.delete(item.toEntity())
    }
}