package com.wonderwall.imagecollector.data

import com.wonderwall.imagecollector.domain.repository.KakaoApiRepository
import retrofit2.Response
import javax.inject.Inject

class KakaoApiRepositoryImpl @Inject constructor(
    private val api: KakaoApi
) : KakaoApiRepository {
    override suspend fun getImageList(
        keyword: String,
        sort: String?,
        page: Int?,
        size: Int?
    ): Response<KakaoBaseResponse> = api.getImageList(keyword, sort, page, size)

    override suspend fun getVideoList(
        keyword: String,
        sort: String?,
        page: Int?,
        size: Int?
    ): Response<KakaoBaseResponse> = api.getVideoList(keyword, sort, page, size)
}