package com.music.infinity.domain.model

import com.music.infinity.common.model.Image

data class Playlist(
    val description: String,
    val href: String,
    val id: String,
    val images: List<Image>,
    val name: String,
)
