package com.music.infinity.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseWrapper<out T>(
    @SerialName("albums")
    val albums: T
)
