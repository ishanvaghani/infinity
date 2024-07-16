package com.music.infinity.data.repository

import arrow.core.Either
import com.music.infinity.data.remote.NetworkManager
import com.music.infinity.data.remote.SpotifyApi
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.ArtistAlbum
import com.music.infinity.domain.model.ArtistInfo
import com.music.infinity.domain.model.RelatedArtist
import com.music.infinity.domain.repository.ArtistRepository

class ArtistRepositoryImpl(
    private val spotifyApi: SpotifyApi,
    private val networkManager: NetworkManager
) : ArtistRepository {

    override suspend fun getArtistInfo(artistId: String): Either<Failure, ArtistInfo> {
        val response = networkManager.executeWithAuthentication {
            spotifyApi.getArtistInfo(artistId);
        }

        return response.map { it.toArtistInfo() }
    }

    override suspend fun getArtistAlbumList(
        id: String,
    ): Either<Failure, List<ArtistAlbum>> {
        val response = networkManager.executeWithAuthentication {
            spotifyApi.getArtistAlbums(id)
        }
        return response.map { it.toArtistAlbumWrapper().items }
//        return response.map { it.items.map { it.toArtistAlbum() } }
    }

    override suspend fun getRelatedArtist(id: String): Either<Failure, RelatedArtist> {
        val response = networkManager.executeWithAuthentication {
            spotifyApi.getRelatedArtists(id);
        }

        return response.map { it.toRelatedArtistList() }
    }

}
