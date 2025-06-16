package com.wonderwall.imagecollector.presentation.search

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.wonderwall.imagecollector.core.Constants.TAG
import com.wonderwall.imagecollector.presentation.MainViewModel
import com.wonderwall.imagecollector.ui.theme.ImageCollectorTheme
import com.wonderwall.imagecollector.ui.theme.component.SearchBar


@Composable
fun SearchScreen(
    viewModel: MainViewModel
) {
    val keyword by viewModel.keyword.collectAsState()
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.padding(it)) {
            Column {
                SearchBar(
                    keyword = keyword,
                    onKeywordChange = { viewModel.setKeyword(it) },
                    onSearch = {
                        Log.d(TAG, "SearchScreen: $it")
                        viewModel.search(keyword)
                    }
                )
            }
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