package com.example.podcastapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.podcastapp.NavGraphs
import com.example.podcastapp.ui.common.BottomNavigationBar
import com.example.podcastapp.ui.theme.PodcastAppTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            PodcastApp()
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PodcastApp() {
    val navController = rememberNavController()

    PodcastAppTheme {
        Scaffold(
            modifier = Modifier
                .navigationBarsPadding()
                .imePadding()
                .fillMaxSize(),
            backgroundColor = MaterialTheme.colors.background,
            bottomBar = { BottomNavigationBar(navController = navController) }
        ) {
            DestinationsNavHost(navController = navController, navGraph = NavGraphs.root)
        }
    }
}