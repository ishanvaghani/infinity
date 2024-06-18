package com.music.infinity.domain.usecase

import arrow.core.Either
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.SearchList
import com.music.infinity.domain.repository.SearchRepository

class SearchUseCase(private val searchRepository: SearchRepository) {

    suspend fun search(query: String, type: String, offset: Int): Either<Failure, SearchList> {
        return searchRepository.search(query, type, offset)
    }
}