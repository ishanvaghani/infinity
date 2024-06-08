package com.music.infinity.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<out T>(
    @SerialName("albums")
    val albums: T? = null,
    @SerialName("error")
    val error: Error? = null
)