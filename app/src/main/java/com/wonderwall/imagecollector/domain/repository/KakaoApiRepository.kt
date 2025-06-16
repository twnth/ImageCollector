package com.wonderwall.imagecollector.domain.repository

import com.wonderwall.imagecollector.data.KakaoBaseResponse
import retrofit2.Response

interface KakaoApiRepository {
    suspend fun getImageList(
        keyword: String,
        sort: String? = null,
        page: Int? = null,
        size: Int? = null
    ): Response<KakaoBaseResponse>

    suspend fun getVideoList(
        keyword: String,
        sort: String? = null,
        page: Int? = null,
        size: Int? = null
    ): Response<KakaoBaseResponse>
}