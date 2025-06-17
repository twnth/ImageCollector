package com.wonderwall.imagecollector.presentation.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun BottomNavigationBar(
    items: List<Routes>,
    currentRoute: String?,
    onItemClick: (Routes) -> Unit
) {
    NavigationBar(
        modifier = Modifier
//            .navigationBarsPadding()
            .fillMaxWidth()
            .height(100.dp)
            .navigationBarsPadding() // 하단 시스템 바와 겹치지 않도록
    ) {
        items.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = { onItemClick(screen) },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.label,
                    )
                },
//                label = { Text(screen.label) }
            )
        }
    }
}