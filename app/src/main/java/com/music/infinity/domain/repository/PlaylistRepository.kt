package com.music.infinity.domain.repository

import arrow.core.Either
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.Playlist
import com.music.infinity.domain.model.PlaylistList

interface PlaylistRepository {

    suspend fun getFeaturedPlaylists(): Either<Failure, PlaylistList>

    suspend fun getPlaylist(id: String): Either<Failure, Playlist>
}