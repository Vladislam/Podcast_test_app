package com.example.podcastapp.util

import com.example.podcastapp.domain.model.UiText

sealed class Resource<T>(val data: T? = null, val message: UiText? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(msg: UiText, data: T? = null) : Resource<T>(data, msg)
    class Loading<T>(val isLoading: Boolean = true) : Resource<T>()
}
