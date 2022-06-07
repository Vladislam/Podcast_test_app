package com.example.podcastapp.ui.common

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.podcastapp.R
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun BackButton(navigator: DestinationsNavigator, modifier: Modifier = Modifier) {
    IconButton(modifier = modifier, onClick = { navigator.popBackStack() }) {
        Icon(
            Icons.Default.ArrowBack,
            contentDescription = stringResource(R.string.back),
        )
    }
}