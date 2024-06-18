package com.music.infinity.data.repository

import arrow.core.Either
import com.music.infinity.data.remote.NetworkManager
import com.music.infinity.data.remote.SpotifyApi
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.SearchList
import com.music.infinity.domain.repository.SearchRepository

class SearchRepositoryImpl(
    private val spotifyApi: SpotifyApi,
    private val networkManager: NetworkManager
) : SearchRepository {

    override suspend fun search(
        query: String,
        type: String,
        offset: Int
    ): Either<Failure, SearchList> {
        val result = networkManager.executeWithAuthentication {
            spotifyApi.search(query, type, offset)
        }
        return result.map { it.toSearchList() }
    }
}