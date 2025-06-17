package com.wonderwall.imagecollector.ui.theme.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wonderwall.imagecollector.domain.model.ContentsItem
import com.wonderwall.imagecollector.presentation.MainViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@Composable
fun ContentsListScreen(
    viewModel: MainViewModel,
    initialList: List<ContentsItem>? = null
) {
    val searchedList = viewModel.combinedList.collectAsState()
    val favoriteList = viewModel.galleryList.collectAsState()
    val gridState = rememberLazyGridState()

    // 현재 보여줄 리스트 (검색 리스트 / 보관함 기준으로 데이터 나뉨)
    val currentList = initialList ?: searchedList.value
    val isSearchedList = initialList == null

    // 검색 리스트인 경우에만 페이징 처리
    if (isSearchedList) {
        LaunchedEffect(gridState) {
            snapshotFlow { gridState.layoutInfo }
                .map { it.visibleItemsInfo.lastOrNull()?.index }
                .distinctUntilChanged()
                .collect { lastVisibleIndex ->
                    if (lastVisibleIndex != null && lastVisibleIndex >= currentList.size - 10) {
                        viewModel.searchNextPage()
                    }
                }
        }
    }

    val favoriteIdxSet = remember(favoriteList.value) {
        favoriteList.value.map { it.thumbnailUrl }.toSet()
    }

    LazyVerticalGrid(
        state = gridState,
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(currentList) { item ->
            val isFavorite = favoriteIdxSet.contains(item.thumbnailUrl)
            ContentsItemCard(item.copy(isFavorite = isFavorite)) { selectedItem ->
                if (isFavorite) {
                    viewModel.deleteToGallery(selectedItem)
                } else {
                    viewModel.saveToGallery(selectedItem)
                }
            }
        }
    }
}