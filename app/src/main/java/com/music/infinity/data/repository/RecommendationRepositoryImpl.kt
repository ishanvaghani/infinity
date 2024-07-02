package com.music.infinity.data.repository

import arrow.core.Either
import com.music.infinity.data.remote.NetworkManager
import com.music.infinity.data.remote.SpotifyApi
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.RecommendationList
import com.music.infinity.domain.repository.RecommendationRepository

class RecommendationRepositoryImpl(
    private val spotifyApi: SpotifyApi,
    private val networkManager: NetworkManager
) : RecommendationRepository {

    override suspend fun getRecommendations(genres: String?): Either<Failure, RecommendationList> {
        val response = networkManager.executeWithAuthentication {
            spotifyApi.getRecommendations(genres)
        }
        return response.map { it.toRecommendationList() }
    }
}