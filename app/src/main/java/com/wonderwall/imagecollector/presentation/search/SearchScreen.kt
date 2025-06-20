package com.wonderwall.imagecollector.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wonderwall.imagecollector.presentation.MainViewModel
import com.wonderwall.imagecollector.ui.theme.ImageCollectorTheme
import com.wonderwall.imagecollector.ui.theme.component.ContentsListScreen
import com.wonderwall.imagecollector.ui.theme.component.SearchBar


@Composable
fun SearchScreen(
    viewModel: MainViewModel
) {
    val keyword by viewModel.keyword.collectAsState()

    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column {
            SearchBar(
                keyword = keyword,
                onKeywordChange = { viewModel.setKeyword(it) },
                onSearch = {
                    viewModel.setInitialData()
                }
            )
            ContentsListScreen(viewModel)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreView() {
    ImageCollectorTheme {
        SearchScreen(hiltViewModel())
    }
}