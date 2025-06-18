package com.wonderwall.imagecollector.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.wonderwall.imagecollector.presentation.navigation.NavigationPager
import com.wonderwall.imagecollector.ui.theme.ImageCollectorTheme
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
fun ImageCollectorApp(viewModel: MainViewModel) {
    NavigationPager(viewModel)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImageCollectorTheme {
        ImageCollectorApp(hiltViewModel())
    }
}