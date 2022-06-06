package com.example.podcastapp.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.podcastapp.R
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter


fun Long.formatMillisecondsAsDate(
    pattern: String = "yyyy-MM-dd HH:mm:ss"
): String {
    val dateFormatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault())
    val instant = Instant.ofEpochMilli(this)
    return dateFormatter.format(instant)
}

@Composable
fun Int.toDurationMinutes(): String {
    val minutes = (this / 60)

    return stringResource(R.string.minutes_template, minutes.toString())
}