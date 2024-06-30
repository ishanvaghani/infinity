package com.music.infinity.data.remote.dto

import com.music.infinity.common.model.Image
import com.music.infinity.domain.model.ArtistInfo
import com.music.infinity.domain.model.Followers
import kotlinx.serialization.SerialName

class ArtistInfoDto(
    @SerialName("external_urls")
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
) {

    fun toArtistInfo() : ArtistInfo{
        return ArtistInfo(
            externalUrls, followers, genres, href, id, images, name, popularity, type, uri
        )
    }

}

