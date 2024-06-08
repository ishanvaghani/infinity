package com.music.infinity.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumWrapperDto(
    @SerialName("href")
    val href: String,
    @SerialName("items")
    val albumDtos: List<AlbumDto>,
    @SerialName("limit")
    val limit: Int,
    @SerialName("next")
    val next: String?,
    @SerialName("offset")
    val offset: Int,
    @SerialName("previous")
    val previous: String?,
    @SerialName("total")
    val total: Int
)