package com.music.infinity.domain.usecase

import arrow.core.Either
import com.music.infinity.data.remote.dto.ArtistInfoDto
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.data.repository.ArtistRepositoryImpl
import com.music.infinity.domain.model.Album
import com.music.infinity.domain.model.Artist
import com.music.infinity.domain.model.ArtistAlbum
import com.music.infinity.domain.model.ArtistInfo
import com.music.infinity.domain.repository.ArtistRepository

class ArtistUseCase(private val artistRepository: ArtistRepository) {
    suspend fun getArtistInfo(artistId: String): Either<Failure, ArtistInfo> {
        return artistRepository.getArtistInfo(artistId)
    }

    suspend fun getArtistAlbum(artistId: String, lstGroups : List<String> , market: String): Either<Failure, List<ArtistAlbum>> {
        return artistRepository.getArtistAlbumList(artistId, lstGroups, market)
    }

}