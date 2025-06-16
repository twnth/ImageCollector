package com.wonderwall.imagecollector.core

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Failed(val message: String?, val throwable: Throwable?) : ApiResult<Nothing>()
}