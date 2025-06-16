package com.wonderwall.imagecollector.presentation

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.wonderwall.imagecollector.core.ApiResult
import com.wonderwall.imagecollector.data.KakaoBaseResponse
import com.wonderwall.imagecollector.domain.usecase.KakaoApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val kakaoApiUseCase: KakaoApiUseCase,
) : BaseViewModel() {
    private var _keyword: MutableStateFlow<String> = MutableStateFlow("")
    val keyword = _keyword.asStateFlow()

    private var _imageList: MutableStateFlow<KakaoBaseResponse> =
        MutableStateFlow(KakaoBaseResponse())
    val imageList = _imageList.asStateFlow()

    private var _videoList: MutableStateFlow<KakaoBaseResponse> =
        MutableStateFlow(KakaoBaseResponse())
    val videoList = _videoList.asStateFlow()

    fun setKeyword(keyword: String) {
        _keyword.value = keyword
    }

    fun search(keyword: String) {
        searchImage(keyword)
        searchVideo(keyword)
    }

    private fun searchImage(keyword: String) = viewModelScope.launch {
        val result = kakaoApiUseCase.getImageList(keyword)
        handleApiResult(context, result) {
            _imageList.value = it
        }
    }

    private fun searchVideo(keyword: String) = viewModelScope.launch {
        val result = kakaoApiUseCase.getVideoList(keyword)
        handleApiResult(context, result) {
            _videoList.value = it
        }
    }
}