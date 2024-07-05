package com.music.infinity.domain.usecase

import arrow.core.Either
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.Playlist
import com.music.infinity.domain.model.PlaylistList
import com.music.infinity.domain.repository.PlaylistRepository

class PlaylistUseCase(private val playlistRepository: PlaylistRepository) {

    suspend fun getFeaturedPlaylists(): Either<Failure, PlaylistList> {
        return playlistRepository.getFeaturedPlaylists()
    }

    suspend fun getPlaylist(id: String): Either<Failure, Playlist> {
        return playlistRepository.getPlaylist(id)
    }
}