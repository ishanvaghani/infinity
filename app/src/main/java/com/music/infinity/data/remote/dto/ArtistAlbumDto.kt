package com.music.infinity.data.remote.dto

import com.music.infinity.common.model.Image
import com.music.infinity.domain.model.Artist
import com.music.infinity.domain.model.ArtistAlbum
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ArtistAlbumDto(
    @SerialName("album_type")
    val albumType: String,
    @SerialName("total_tracks")
    val totalTracks: Long,
    @SerialName("available_markets")
    val availableMarkets: List<String>? = null,
    @SerialName("external_urls")
    val externalUrls: ExternalUrlsDto,
    @SerialName("href")
    val href: String,
    @SerialName("id")
    val id: String,
    @SerialName("images")
    val images: List<Image>,
    @SerialName("name")
    val name: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("release_date_precision")
    val releaseDatePrecision: String,
    @SerialName("type")
    val type: String,
    @SerialName("uri")
    val uri: String,
    @SerialName("artists")
    val artists: List<ArtistDto>,
    @SerialName("album_group")
    val albumGroup: String,
) {
    fun toArtistAlbum(): ArtistAlbum {
        return ArtistAlbum(
            albumType, totalTracks, availableMarkets, externalUrls, href, id, images,
            name, releaseDate, releaseDatePrecision, type, uri, artists.map { it.toArtist() }, albumGroup
        )
    }
}