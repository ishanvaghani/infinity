package com.music.infinity.data.repository

import arrow.core.Either
import com.music.infinity.data.remote.NetworkManager
import com.music.infinity.data.remote.SpotifyApi
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.Album
import com.music.infinity.domain.model.Categories
import com.music.infinity.domain.repository.AlbumRepository
import com.music.infinity.domain.repository.CategoriesRepository

class CategoriesRepositoryImpl(
    private val spotifyApi: SpotifyApi,
    private val networkManager: NetworkManager
) : CategoriesRepository {
    override suspend fun getCategories(): Either<Failure, List<Categories>> {
        return networkManager.executeWithAuthentication {
            spotifyApi.getCategories(0)
        }
    }
}