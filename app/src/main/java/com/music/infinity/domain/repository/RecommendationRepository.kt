package com.music.infinity.domain.repository

import arrow.core.Either
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.RecommendationList

interface RecommendationRepository {

    suspend fun getRecommendations(genres: String?): Either<Failure, RecommendationList>
}