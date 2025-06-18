package com.wonderwall.imagecollector.presentation.gallery

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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

@Composable
fun GalleryScreen(viewModel: MainViewModel) {
    val list by viewModel.galleryList.collectAsState()
    Box(modifier = Modifier.padding(16.dp)) {
        Column {
            ContentsListScreen(viewModel = viewModel, initialList = list)
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