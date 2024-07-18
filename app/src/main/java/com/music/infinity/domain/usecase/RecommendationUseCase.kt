package com.music.infinity.domain.usecase

import arrow.core.Either
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.RecommendationList
import com.music.infinity.domain.repository.RecommendationRepository

class RecommendationUseCase(private val recommendationRepository: RecommendationRepository) {

    suspend fun getRecommendations(genre: String?): Either<Failure, RecommendationList> {
        return recommendationRepository.getRecommendations(genre)
    }
}