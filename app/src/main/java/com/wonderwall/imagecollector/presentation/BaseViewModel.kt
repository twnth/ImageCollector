package com.wonderwall.imagecollector.presentation

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.wonderwall.imagecollector.core.ApiResult
import com.wonderwall.imagecollector.core.Constants
import com.wonderwall.imagecollector.data.KakaoBaseResponse
import com.wonderwall.imagecollector.domain.model.ContentsItem
import com.wonderwall.imagecollector.domain.model.ContentsType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {
    protected fun handleApiResult(
        type: ContentsType,
        context: Context,
        result: ApiResult<KakaoBaseResponse>?,
        onSuccess: (KakaoBaseResponse) -> Unit
    ): List<ContentsItem> {
        return when (result) {
            is ApiResult.Success -> {
                onSuccess(result.data)
                result.data.documents.map {
                    Log.d(Constants.TAG, "handleApiResult: ${it.thumbnail} / ${it.thumbnailUrl}")
                    ContentsItem(
                        thumbnailUrl = if (type == ContentsType.IMAGE) it.thumbnailUrl else it.thumbnail,
                        dateTime = it.datetime,
                        type = type
                    )
                }
            }

            is ApiResult.Failed -> {
                Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                emptyList()
            }

            else -> {
                Toast.makeText(context, "알 수 없는 에러 발생!", Toast.LENGTH_SHORT).show()
                emptyList()
            }
        }
    }
}