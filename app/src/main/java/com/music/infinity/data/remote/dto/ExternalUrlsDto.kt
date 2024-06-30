package com.music.infinity.data.remote.dto

import com.music.infinity.domain.model.CategoriesList
import com.music.infinity.domain.model.ExternalUrls

data class ExternalUrlsDto(
    val spotify: String
){
    fun toExternalUrls(): ExternalUrls {
        return ExternalUrls(spotify)
    }
}
