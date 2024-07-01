package com.music.infinity.data.remote.dto

import com.music.infinity.domain.model.Genre
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenresDto(
    @SerialName("genres")
    val genres: List<String>
) {
    fun toGenre(): Genre {
        return Genre(genres)
    }
}