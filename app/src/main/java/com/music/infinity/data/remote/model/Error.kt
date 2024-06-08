package com.music.infinity.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Error(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String
)
