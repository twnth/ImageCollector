package com.wonderwall.imagecollector.domain.usecase

import com.wonderwall.imagecollector.domain.model.ContentsItem
import com.wonderwall.imagecollector.domain.repository.SearchedListRepository
import javax.inject.Inject

class SearchedListUseCase @Inject constructor(
    private val repository: SearchedListRepository
) {
    suspend fun getSearchedList(keyword: String): List<ContentsItem>? =
        repository.getSearchedList(keyword)

    suspend fun saveCache(
        keyword: String,
        list: List<ContentsItem>
    ) = repository.saveCache(keyword, list)
}