package com.music.infinity.domain.model

import com.music.infinity.common.model.Image

data class Artist(
    val href: String,
    val id: String,
    val name: String,
    val type: String,
    val uri: String,
    val images: List<Image>?
)