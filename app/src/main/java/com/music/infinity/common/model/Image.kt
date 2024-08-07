package com.music.infinity.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    @SerialName("height")
    val height: Int?,
    @SerialName("width")
    val width: Int?,
    @SerialName("url")
    val url: String
)