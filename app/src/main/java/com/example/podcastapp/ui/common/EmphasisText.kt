package com.example.podcastapp.ui.common

import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun EmphasisText(
    text: String,
    modifier: Modifier = Modifier,
    contentAlpha: Float = ContentAlpha.medium,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    style: TextStyle = MaterialTheme.typography.body2
) {
    CompositionLocalProvider(LocalContentAlpha provides contentAlpha) {
        Text(
            text,
            modifier = modifier,
            style = style,
            maxLines = maxLines,
            overflow = overflow,
        )
    }
}