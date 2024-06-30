package com.music.infinity.data.remote.dto

import com.music.infinity.common.model.Image
import com.music.infinity.domain.model.Artist
import com.music.infinity.domain.model.ArtistAlbum
import kotlinx.serialization.SerialName

class ArtistAlbumDto(
    @SerialName("album_type")
    val albumType: String,
    @SerialName("total_tracks")
    val totalTracks: Long,
    @SerialName("available_markets")
    val availableMarkets: List<String>,
    @SerialName("external_urls")
    val externalUrls: ExternalUrlsDto,
    val href: String,
    val id: String,
    val images: List<Image>,
    val name: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("release_date_precision")
    val releaseDatePrecision: String,
    val type: String,
    val uri: String,
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