package com.music.infinity.data.repository

import arrow.core.Either
import com.music.infinity.data.remote.NetworkManager
import com.music.infinity.data.remote.SpotifyApi
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.Categories
import com.music.infinity.domain.model.Genre
import com.music.infinity.domain.repository.CategoriesRepository
import com.music.infinity.domain.repository.GenreRepository

class GenreRepositoryImpl(
    private val spotifyApi: SpotifyApi,
    private val networkManager: NetworkManager
) : GenreRepository {
    override suspend fun getGenre(): Either<Failure, Genre> {
        return networkManager.executeWithAuthentication {
            spotifyApi.getGenres()
        }
    }
}