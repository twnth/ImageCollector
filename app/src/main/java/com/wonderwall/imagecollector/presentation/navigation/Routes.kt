package com.wonderwall.imagecollector.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Routes(val route: String, val label: String, val icon: ImageVector) {
    object Search : Routes("search", "검색", Icons.Default.Search)
    object Gallery : Routes("gallery", "보관함", Icons.Default.Favorite)
}