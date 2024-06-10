package com.music.infinity.domain.model

import com.music.infinity.common.model.Image

data class Categories(
    val href: String,
    val icons: List<Image>,
    val id: String,
    val name: String,
)
