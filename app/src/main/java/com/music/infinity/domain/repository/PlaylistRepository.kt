package com.music.infinity.domain.repository

import arrow.core.Either
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.PlaylistList

interface PlaylistRepository {

    suspend fun getFeaturedPlaylists(): Either<Failure, PlaylistList>
}