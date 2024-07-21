package com.music.infinity.presentation.artist.models

import androidx.compose.runtime.Stable
import com.music.infinity.domain.model.Album
import com.music.infinity.domain.model.Artist
import com.music.infinity.domain.model.ArtistAlbum
import com.music.infinity.domain.model.ArtistInfo
import com.music.infinity.domain.model.Playlist
import com.music.infinity.presentation.home.models.HomeAction

@Stable
sealed interface ArtistInfoAction {
    data class AlbumClick(val album: ArtistAlbum) : ArtistInfoAction

    data class ArtistClick(val artist: ArtistInfo) : ArtistInfoAction


}
