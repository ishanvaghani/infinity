package com.music.infinity.presentation.genres.models

import androidx.compose.runtime.Stable

@Stable
data class GenresState(
    val genres: List<String>,
    val selectedGenres: List<String>
)
