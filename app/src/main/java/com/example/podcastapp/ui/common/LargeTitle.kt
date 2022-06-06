package com.example.podcastapp.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LargeTitle(title: String) {
    Box(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(bottom = 32.dp)
            .height(70.dp),
    ) {
        Text(
            title,
            style = MaterialTheme.typography.h1,
            modifier = Modifier.align(Alignment.BottomStart)
        )
    }
}