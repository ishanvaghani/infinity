package com.music.infinity.domain.usecase

import arrow.core.Either
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.Genre
import com.music.infinity.domain.repository.GenreRepository

class GenreUseCase(private val genreRepository: GenreRepository) {

    suspend fun getGenres(): Either<Failure, Genre> {
        return genreRepository.getGenres()
    }
}