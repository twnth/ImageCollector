package com.wonderwall.imagecollector.presentation

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.wonderwall.imagecollector.data.KakaoBaseResponse
import com.wonderwall.imagecollector.domain.model.ContentsItem
import com.wonderwall.imagecollector.domain.model.ContentsType
import com.wonderwall.imagecollector.domain.usecase.KakaoApiUseCase
import com.wonderwall.imagecollector.domain.usecase.SearchedListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val kakaoApiUseCase: KakaoApiUseCase,
    private val searchedListUseCase: SearchedListUseCase,
) : BaseViewModel() {
    private var _keyword: MutableStateFlow<String> = MutableStateFlow("")
    val keyword = _keyword.asStateFlow()

    private var _imageList: MutableStateFlow<KakaoBaseResponse> =
        MutableStateFlow(KakaoBaseResponse())
    val imageList = _imageList.asStateFlow()

    private var _videoList: MutableStateFlow<KakaoBaseResponse> =
        MutableStateFlow(KakaoBaseResponse())
    val videoList = _videoList.asStateFlow()

    private val _combinedList = MutableStateFlow<List<ContentsItem>>(emptyList())
    val combinedList = _combinedList.asStateFlow()

    fun setKeyword(keyword: String) {
        _keyword.value = keyword
    }

    fun setData(keyword: String) = viewModelScope.launch {
        val cachedData = getCachedData(keyword)
        if (cachedData !== null) {
            _combinedList.value = cachedData
            return@launch
        }
        search(keyword)
    }

    private fun search(keyword: String) = viewModelScope.launch {
        val imageDeferred = async { kakaoApiUseCase.getImageList(keyword) }
        val videoDeferred = async { kakaoApiUseCase.getVideoList(keyword) }

        val imageResult = imageDeferred.await()
        val videoResult = videoDeferred.await()

        val imageItems = handleApiResult(ContentsType.IMAGE, context, imageResult) {}
        val videoItems = handleApiResult(ContentsType.VIDEO, context, videoResult) {}

        val mergedList = (imageItems + videoItems)
            .sortedByDescending { it.dateTime }
            .mapIndexed { index, item -> // 보관함 식별 위한 idx 추가
                item.copy(idx = index)
            }
        searchedListUseCase.saveCache(keyword, mergedList)
        _combinedList.value = mergedList
    }

    private suspend fun getCachedData(keyword: String): List<ContentsItem>? {
        val cached = searchedListUseCase.getSearchedList(keyword)
        if (cached != null) {
            return cached.mapIndexed { index, item -> item.copy(idx = index) }
        }
        return null
    }

    private fun searchImage(keyword: String) = viewModelScope.launch {
        val result = kakaoApiUseCase.getImageList(keyword)
        handleApiResult(ContentsType.IMAGE, context, result) {
            _imageList.value = it
        }
    }

    private fun searchVideo(keyword: String) = viewModelScope.launch {
        val result = kakaoApiUseCase.getVideoList(keyword)
        handleApiResult(ContentsType.VIDEO, context, result) {
            _videoList.value = it
        }
    }
}