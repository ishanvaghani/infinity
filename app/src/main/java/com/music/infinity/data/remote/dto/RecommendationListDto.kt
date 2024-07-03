package com.music.infinity.data.remote.dto

import com.music.infinity.domain.model.RecommendationList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendationListDto(
    @SerialName("tracks")
    val tracks: List<TrackDto>
) {

    fun toRecommendationList(): RecommendationList {
        return RecommendationList(tracks.map { it.toTrack() })
    }
}
