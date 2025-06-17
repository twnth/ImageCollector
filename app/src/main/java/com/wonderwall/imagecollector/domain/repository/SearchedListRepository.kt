package com.wonderwall.imagecollector.domain.repository

import com.wonderwall.imagecollector.domain.model.ContentsItem

interface SearchedListRepository {
    suspend fun getSearchedList(keyword: String): List<ContentsItem>?
    suspend fun saveCache(keyword: String, list: List<ContentsItem>)
}