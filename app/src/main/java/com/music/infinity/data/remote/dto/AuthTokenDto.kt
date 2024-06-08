package com.music.infinity.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthTokenDto(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("token_type")
    val tokenType: String,
    @SerialName("expires_in")
    val expiresIn: Long
)