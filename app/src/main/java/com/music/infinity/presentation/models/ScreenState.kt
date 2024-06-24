package com.music.infinity.presentation.models

data class ScreenState<T>(
    val isLoading: Boolean,
    val error: ScreenError? = null,
    val data: T? = null
)