package com.wonderwall.imagecollector.presentation

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.wonderwall.imagecollector.domain.model.ContentsItem
import com.wonderwall.imagecollector.domain.model.ContentsType
import com.wonderwall.imagecollector.domain.usecase.GalleryUseCase
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
    private val galleryUseCase: GalleryUseCase,
    private val kakaoApiUseCase: KakaoApiUseCase,
    private val searchedListUseCase: SearchedListUseCase,
) : BaseViewModel() {
    companion object {
        private const val PAGE_SIZE = 30
    }

    private var currentPage = 1
    private var isLoading = false
    private var lastSearchedKeyword = ""

    private var _keyword: MutableStateFlow<String> = MutableStateFlow("")
    val keyword = _keyword.asStateFlow()

    private val _combinedList = MutableStateFlow<List<ContentsItem>>(emptyList())
    val combinedList = _combinedList.asStateFlow()

    private val _galleryList = MutableStateFlow<List<ContentsItem>>(emptyList())
    val galleryList = _galleryList.asStateFlow()

    init {
        getGalleryList()
    }

    fun setKeyword(keyword: String) {
        _keyword.value = keyword
    }

    fun setInitialData() = viewModelScope.launch {
        val keyword = keyword.value
        val cachedData = getCachedData(keyword)

        if (!cachedData.isNullOrEmpty()) {
            _combinedList.value = cachedData
            lastSearchedKeyword = keyword
            currentPage = (cachedData.size / PAGE_SIZE) + 1
        } else {
            _combinedList.value = emptyList()
            currentPage = 1
            lastSearchedKeyword = ""
            search()
        }
    }

    fun searchNextPage() {
        if (!isLoading) {
            search()
        }
    }

    fun search() = viewModelScope.launch {
        isLoading = true
        val keyword = keyword.value
        if (lastSearchedKeyword != keyword) {
            currentPage = 1
            _combinedList.value = emptyList()
        }

        val imageDeferred =
            async {
                kakaoApiUseCase.getImageList(
                    keyword = keyword,
                    size = PAGE_SIZE,
                    page = currentPage
                )
            }
        val videoDeferred =
            async {
                kakaoApiUseCase.getVideoList(
                    keyword = keyword,
                    size = PAGE_SIZE,
                    page = currentPage
                )
            }

        val imageResult = imageDeferred.await()
        val videoResult = videoDeferred.await()

        val imageItems = handleApiResult(ContentsType.IMAGE, context, imageResult) {}
        val videoItems = handleApiResult(ContentsType.VIDEO, context, videoResult) {}

        val favoriteIdxSet = galleryList.value.map { it.thumbnailUrl }.toSet()

        val newItems = (imageItems + videoItems)
            .sortedByDescending { it.dateTime }
            .mapIndexed { index, item ->
                item.copy(
                    idx = _combinedList.value.size + index,
                    isFavorite = item.thumbnailUrl in favoriteIdxSet
                )
            }

        val merged = _combinedList.value + newItems

        _combinedList.value = merged

        searchedListUseCase.saveCache(keyword, merged)

        lastSearchedKeyword = keyword
        currentPage++
        isLoading = false
    }

    fun getGalleryList() = viewModelScope.launch {
        _galleryList.value = galleryUseCase.getAll()
    }

    fun saveToGallery(item: ContentsItem) = viewModelScope.launch {
        galleryUseCase.save(item)
        getGalleryList()
    }

    fun deleteToGallery(item: ContentsItem) = viewModelScope.launch {
        galleryUseCase.delete(item)
        getGalleryList()
    }

    private suspend fun getCachedData(keyword: String): List<ContentsItem>? {
        val cached = searchedListUseCase.getSearchedList(keyword)
        return cached?.mapIndexed { index, item -> item.copy(idx = index) }
    }
}