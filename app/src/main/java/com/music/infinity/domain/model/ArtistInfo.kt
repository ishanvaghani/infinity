package com.music.infinity.domain.model

import com.music.infinity.common.model.Image
import com.music.infinity.data.remote.dto.ExternalUrlsDto

data class ArtistInfo(
    val externalUrls: ExternalUrlsDto,
    val followers: Followers,
    val genres: List<String>,
    val href: String,
    val id: String,
    val images: List<Image>,
    val name: String,
    val popularity: Long,
    val type: String,
    val uri: String,
)
