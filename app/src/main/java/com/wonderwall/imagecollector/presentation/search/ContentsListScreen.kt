package com.wonderwall.imagecollector.presentation.search

import android.os.Build
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.wonderwall.imagecollector.core.Constants
import com.wonderwall.imagecollector.core.parseToLocalDateTimeWithOffset
import com.wonderwall.imagecollector.domain.model.ContentsItem
import com.wonderwall.imagecollector.presentation.MainViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@Composable
fun ContentsListScreen(viewModel: MainViewModel) {
    val list = viewModel.combinedList.collectAsState()
    val gridState = rememberLazyGridState()

    LaunchedEffect(gridState) {
        snapshotFlow { gridState.layoutInfo }
            .map { it.visibleItemsInfo.lastOrNull()?.index }
            .distinctUntilChanged()
            .collect { lastVisibleIndex ->
                if (lastVisibleIndex != null && lastVisibleIndex >= list.value.size - 10) {
                    viewModel.searchNextPage()
                }
            }
    }

    LazyVerticalGrid(
        state = gridState,
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(list.value) { item ->
            ContentsItemCard(item) { selectedItemIdx ->
                Log.d(Constants.TAG, "ContentsListScreen: $selectedItemIdx")
            }
        }
    }
}

@Composable
fun ContentsItemCard(item: ContentsItem, onClick: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .clickable { onClick(item.idx) },
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = item.thumbnailUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
//        Text(
//            modifier = Modifier.align(Alignment.TopStart),
//            text = item.type.name
//        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Text(
                modifier = Modifier.align(Alignment.BottomCenter),
                text = item.dateTime.parseToLocalDateTimeWithOffset()
            )
        } else {
            Text(text = item.dateTime)
        }
    }
}