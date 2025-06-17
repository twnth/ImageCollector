package com.wonderwall.imagecollector.presentation.gallery

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.wonderwall.imagecollector.presentation.MainViewModel
import com.wonderwall.imagecollector.ui.theme.component.ContentsListScreen
import com.wonderwall.imagecollector.ui.theme.ImageCollectorTheme

@Composable
fun GalleryScreen(viewModel: MainViewModel) {
    val list by viewModel.galleryList.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.padding(it)) {
            Column {
                ContentsListScreen(viewModel = viewModel, initialList = list)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GalleryScreenPreView() {
    ImageCollectorTheme {
        GalleryScreen(hiltViewModel())
    }
}