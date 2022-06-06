package com.example.podcastapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.podcastapp.NavGraphs
import com.example.podcastapp.appCurrentDestinationAsState
import com.example.podcastapp.destinations.*
import com.example.podcastapp.startAppDestination
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
    var showBottomBar by rememberSaveable {
        mutableStateOf(true)
    }
    val currentDestination: Destination = navController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination

    showBottomBar = when (currentDestination) {
        PodcastScreenDestination -> true
        SavedScreenDestination -> true
        SettingsScreenDestination -> true
        PodcastDetailScreenDestination -> false
    }

    PodcastAppTheme {
        Scaffold(
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding()
                .imePadding()
                .fillMaxSize(),
            backgroundColor = MaterialTheme.colors.background,
            bottomBar = {
                BottomNavigationBar(
                    navController = navController,
                    bottomBarState = showBottomBar
                )
            }
        ) {
            DestinationsNavHost(navController = navController, navGraph = NavGraphs.root)
        }
    }
}