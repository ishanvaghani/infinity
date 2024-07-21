package com.music.infinity.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class Followers(
    val href: String?,
    val total: Long,
)
