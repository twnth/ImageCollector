package com.wonderwall.imagecollector.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoApi {
    @GET("/v2/search/image")
    suspend fun getImageList(
        @Query("query") keyword: String,
        @Query("sort") sort: String?,
        @Query("page") page: Int?,
        @Query("size") size: Int?
    ): Response<KakaoBaseResponse>

    @GET("/v2/search/vclip")
    suspend fun getVideoList(
        @Query("query") keyword: String,
        @Query("sort") sort: String?,
        @Query("page") page: Int?,
        @Query("size") size: Int?
    ): Response<KakaoBaseResponse>
}