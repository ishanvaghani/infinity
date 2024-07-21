package com.music.infinity.data.remote.dto

import com.music.infinity.common.model.Image
import com.music.infinity.domain.model.ArtistInfo
import com.music.infinity.domain.model.Followers
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ArtistInfoDto(
    @SerialName("external_urls")
    val externalUrls: ExternalUrlsDto,
    @SerialName("followers")
    val followers: FollowersDto,
    @SerialName("genres")
    val genres: List<String>,
    @SerialName("href")
    val href: String,
    @SerialName("id")
    val id: String,
    @SerialName("images")
    val images: List<Image>,
    @SerialName("name")
    val name: String,
    @SerialName("popularity")
    val popularity: Long,
    @SerialName("type")
    val type: String,
    @SerialName("uri")
    val uri: String,
) {

    fun toArtistInfo() : ArtistInfo{
        return ArtistInfo(
            externalUrls.toExternalUrls(), followers.toFollower(), genres, href, id, images, name, popularity, type, uri
        )
    }

}

