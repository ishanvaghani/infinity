package com.music.infinity.data.remote.dto


import com.music.infinity.common.model.Image
import com.music.infinity.domain.model.Artist
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistDto(
    @SerialName("href")
    val href: String,
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: String,
    @SerialName("uri")
    val uri: String,
    @SerialName("images")
    val images: List<Image>? = null
) {

    fun toArtist(): Artist {
        return Artist(href, id, name, type, uri, images)
    }
}