package com.wonderwall.imagecollector.data.db

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wonderwall.imagecollector.domain.model.ContentsItem
import com.wonderwall.imagecollector.domain.repository.SearchedListRepository
import javax.inject.Inject

class SearchedListRepositoryImpl @Inject constructor(
    private val dao: SearchedListDao,
) : SearchedListRepository {
    private val gson = Gson()
    private val type = object : TypeToken<List<ContentsItem>>() {}.type

    override suspend fun getSearchedList(keyword: String): List<ContentsItem>? {
        val now = System.currentTimeMillis()
        // 키워드 별 캐싱 데이터 확인
        val cache = dao.getCache(keyword)

        // 5분 체크
        val isCacheValid = cache != null && (now - cache.cachedAt < 5 * 60 * 1000)

        return if (isCacheValid) {
            return Gson().fromJson(cache.data, type)
        } else {
            null
        }
    }

    override suspend fun saveCache(
        keyword: String,
        list: List<ContentsItem>
    ) {
        val old = getSearchedList(keyword) ?: emptyList()
        val merged = (old + list).distinctBy { it.idx } // 중복 제거 (idx 기준)
        val json = Gson().toJson(merged)
        dao.insertCache(
            SearchedListEntity(
                keyword = keyword,
                data = json,
                cachedAt = System.currentTimeMillis()
            )
        )
    }
}

