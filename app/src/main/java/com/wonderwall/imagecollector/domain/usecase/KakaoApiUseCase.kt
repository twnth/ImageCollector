package com.wonderwall.imagecollector.domain.usecase

import com.wonderwall.imagecollector.core.ApiResult
import com.wonderwall.imagecollector.data.KakaoBaseResponse
import com.wonderwall.imagecollector.domain.repository.KakaoApiRepository
import javax.inject.Inject

class KakaoApiUseCase @Inject constructor(
    private val repository: KakaoApiRepository
) : BaseUseCase() {
    suspend fun getImageList(
        keyword: String,
        sort: String? = "recency",
        page: Int? = null,
        size: Int? = null
    ): ApiResult<KakaoBaseResponse>? {
        return callApi {
            repository.getImageList(keyword = keyword, size = size, page = page)
        }
    }

    suspend fun getVideoList(
        keyword: String,
        sort: String? = "recency",
        page: Int? = null,
        size: Int? = null
    ): ApiResult<KakaoBaseResponse>? {
        return callApi {
            repository.getVideoList(keyword = keyword, size = size, page = page)
        }
    }
}