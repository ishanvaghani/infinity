package com.music.infinity.presentation.genres.models

import androidx.compose.runtime.Stable

@Stable
sealed interface GenresAction {
    data class GenreClick(val genre: String) : GenresAction
    data object ContinueClick : GenresAction
}