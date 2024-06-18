package com.music.infinity.domain.repository

import arrow.core.Either
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.SearchList

interface SearchRepository {

    suspend fun search(query: String, type: String, offset: Int): Either<Failure, SearchList>
}