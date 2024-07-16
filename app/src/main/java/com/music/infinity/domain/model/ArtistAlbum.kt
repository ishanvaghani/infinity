package com.music.infinity.domain.model

import com.music.infinity.common.model.Image
import com.music.infinity.data.remote.dto.ArtistDto
import com.music.infinity.data.remote.dto.ExternalUrlsDto
import kotlinx.serialization.SerialName

class ArtistAlbum(
    val albumType: String,
    val totalTracks: Long,
    val availableMarkets: List<String>?,
    val externalUrls: ExternalUrlsDto,
    val href: String,
    val id: String,
    val images: List<Image>,
    val name: String,
    val releaseDate: String,
    val releaseDatePrecision: String,
    val type: String,
    val uri: String,
    val artists: List<Artist>,
    val albumGroup: String,
)