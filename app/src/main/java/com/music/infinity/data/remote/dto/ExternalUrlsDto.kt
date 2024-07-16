package com.music.infinity.data.remote.dto

import com.music.infinity.domain.model.CategoriesList
import com.music.infinity.domain.model.ExternalUrls
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalUrlsDto(
    @SerialName("spotify")
    val spotify: String
){
    fun toExternalUrls(): ExternalUrls {
        return ExternalUrls(spotify)
    }
}
