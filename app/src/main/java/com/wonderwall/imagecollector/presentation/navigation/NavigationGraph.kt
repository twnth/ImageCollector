package com.wonderwall.imagecollector.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wonderwall.imagecollector.presentation.MainViewModel
import com.wonderwall.imagecollector.presentation.search.SearchScreen

@Composable
internal fun NavigationGraph(
    navController: NavHostController,
    mainViewModel: MainViewModel,
) {
    NavHost(navController, startDestination = "search") {
        composable("search") { SearchScreen(mainViewModel) }
        composable("gallery") { backStackEntry ->

        }
    }
}