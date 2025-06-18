package com.wonderwall.imagecollector.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.wonderwall.imagecollector.presentation.navigation.BottomNavigationBar
import com.wonderwall.imagecollector.presentation.navigation.NavigationGraph
import com.wonderwall.imagecollector.presentation.navigation.NavigationPager
import com.wonderwall.imagecollector.ui.theme.ImageCollectorTheme
import com.wonderwall.imagecollector.ui.theme.component.MyBottomNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImageCollectorTheme {
                ImageCollectorApp(viewModel)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun ImageCollectorApp(viewModel: MainViewModel) {
    NavigationPager(viewModel)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImageCollectorTheme {
        Greeting("Android")
    }
}