package com.wonderwall.imagecollector.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wonderwall.imagecollector.presentation.MainViewModel
import com.wonderwall.imagecollector.presentation.gallery.GalleryScreen
import com.wonderwall.imagecollector.presentation.search.SearchScreen

@Composable
internal fun NavigationGraph(
    navController: NavHostController,
    mainViewModel: MainViewModel,
) {
    NavHost(
        navController,
        startDestination = "search",
        modifier = Modifier.background(Color.Black)
    ) {
        composable("search") { SearchScreen(mainViewModel) }
        composable("gallery") { GalleryScreen(mainViewModel) }
    }
}