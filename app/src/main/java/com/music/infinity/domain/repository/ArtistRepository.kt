package com.music.infinity.domain.repository

import arrow.core.Either
import com.music.infinity.data.remote.dto.ArtistInfoDto
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.Album
import com.music.infinity.domain.model.ArtistAlbum
import com.music.infinity.domain.model.ArtistInfo
import com.music.infinity.domain.model.RelatedArtist

interface ArtistRepository {

    suspend fun getArtistInfo(artistId:String): Either<Failure, ArtistInfo>

    suspend fun getArtistAlbumList(id : String): Either<Failure, List<ArtistAlbum>>

    suspend fun getRelatedArtist(id: String): Either<Failure, RelatedArtist>

}