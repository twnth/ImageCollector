package com.wonderwall.imagecollector.presentation

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.wonderwall.imagecollector.core.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {
    protected fun <T> handleApiResult(
        context: Context,
        result: ApiResult<T>?,
        onSuccess: (T) -> Unit
    ) {
        when (result) {
            is ApiResult.Success -> onSuccess(result.data)
            is ApiResult.Failed -> {
                Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
            }
            else -> Toast.makeText(context, "알 수 없는 에러 발생!", Toast.LENGTH_SHORT).show()
        }
    }
}