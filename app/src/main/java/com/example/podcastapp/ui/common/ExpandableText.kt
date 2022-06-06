package com.example.podcastapp.ui.common

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun ExpandableText(
    text: String,
    modifier: Modifier = Modifier
) {
    var showMore by remember { mutableStateOf(false) }

    Column(modifier = modifier) {

        Column(modifier = Modifier
            .animateContentSize(animationSpec = tween(100))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { showMore = !showMore }) {

            if (showMore) {
                EmphasisText(text = text)
            } else {
                EmphasisText(text = text, maxLines = 3, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}