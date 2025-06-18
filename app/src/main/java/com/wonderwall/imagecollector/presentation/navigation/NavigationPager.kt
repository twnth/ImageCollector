package com.wonderwall.imagecollector.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.wonderwall.imagecollector.presentation.MainViewModel
import com.wonderwall.imagecollector.presentation.gallery.GalleryScreen
import com.wonderwall.imagecollector.presentation.search.SearchScreen
import kotlinx.coroutines.launch

@Composable
fun NavigationPager(viewModel: MainViewModel) {
    val pagerState = rememberPagerState(initialPage = 0) { 2 }
    val coroutineScope = rememberCoroutineScope()

    val pages = listOf("검색", "보관함")

    // HorizontalPager로 좌우 슬라이드 / BottomNavigation 기능 다 가능하게 구현
    Scaffold(
        bottomBar = {
            NavigationBar {
                pages.forEachIndexed { index, label ->
                    NavigationBarItem(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == 0) Icons.Default.Search else Icons.Default.Favorite,
                                contentDescription = label
                            )
                        },
                        label = { Text(label) }
                    )
                }
            }
        }
    ) { padding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) { page ->
            when (page) {
                0 -> SearchScreen(viewModel)
                1 -> GalleryScreen(viewModel)
            }
        }
    }
}