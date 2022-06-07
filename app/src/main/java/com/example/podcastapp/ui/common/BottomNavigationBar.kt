package com.example.podcastapp.ui.common

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.podcastapp.NavGraphs
import com.example.podcastapp.R
import com.example.podcastapp.appCurrentDestinationAsState
import com.example.podcastapp.destinations.Destination
import com.example.podcastapp.destinations.PodcastScreenDestination
import com.example.podcastapp.destinations.SavedScreenDestination
import com.example.podcastapp.destinations.SettingsScreenDestination
import com.example.podcastapp.startAppDestination
import com.ramcosta.composedestinations.navigation.navigateTo
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

@Composable
fun BottomNavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    bottomBarState: Boolean = true,
) {
    val currentDestination: Destination = navController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination

    AnimatedVisibility(
        visible = bottomBarState,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
    ) {
        BottomNavigation(
            modifier = modifier,
            backgroundColor = Color.Transparent
        ) {
            Row(
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colors.background.copy(alpha = 0.80f),
                                MaterialTheme.colors.background,
                            )
                        )
                    )
                    .then(modifier),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                BottomBarDestination.values().forEach { destination ->
                    BottomNavigationItem(
                        selected = currentDestination == destination.direction,
                        onClick = {
                            navController.navigateTo(destination.direction) {
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = destination.icon),
                                contentDescription = stringResource(id = destination.label)
                            )
                        },
                        label = { Text(stringResource(id = destination.label)) }
                    )
                }
            }
        }
    }
}

enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    @DrawableRes val icon: Int,
    @StringRes val label: Int
) {
    PodcastScreen(PodcastScreenDestination, R.drawable.ic_headset, R.string.podcast),
    SavedScreen(SavedScreenDestination, R.drawable.ic_saved, R.string.saved),
    SettingsScreen(SettingsScreenDestination, R.drawable.ic_settings, R.string.settings)
}