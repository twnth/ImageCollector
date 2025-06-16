package com.wonderwall.imagecollector.domain.usecase

import com.wonderwall.imagecollector.core.ApiResult
import retrofit2.Response

open class BaseUseCase {
    suspend fun <T> callApi(
        call: suspend () -> Response<T>
    ): ApiResult<T>? {
        return try {
            val response = call()
            if (response.isSuccessful && response.body() != null) {
                ApiResult.Success(response.body()!!)
            } else {
                ApiResult.Failed(response.message(), null)
            }
        } catch (e: Exception) {
            ApiResult.Failed(e.message, e)
        }
    }
}