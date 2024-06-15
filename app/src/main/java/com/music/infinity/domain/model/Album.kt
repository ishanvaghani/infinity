package com.music.infinity.domain.model

import com.music.infinity.common.model.Image

data class Album(
    val albumType: String,
    val artists: List<Artist>,
    val id: String,
    val images: List<Image>,
    val name: String,
    val releaseDate: String,
    val totalTracks: Int
)