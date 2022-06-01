package com.example.podcastapp.ui.theme

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = Black1,
    primaryVariant = White50,
    onPrimary = Color.White,
    secondary = Green800,
    onSecondary = Color.White,
    surface = Black1,
    background = Color.Black,
    error = RedErrorLight,
)

@Composable
fun PodcastAppTheme(content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = DarkColorPalette.background,
        darkIcons = false
    )

    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}