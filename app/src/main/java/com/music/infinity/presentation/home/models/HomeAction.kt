package com.music.infinity.presentation.home.models

import androidx.compose.runtime.Stable
import com.music.infinity.domain.model.Album
import com.music.infinity.domain.model.Playlist
import com.music.infinity.domain.model.Track

@Stable
sealed interface HomeAction {
    data class AlbumClick(val album: Album) : HomeAction
    data class PlaylistClick(val playlist: Playlist) : HomeAction
    data class PlaylistPlayClick(val playlist: Playlist) : HomeAction
    data class TrackClick(val track: Track) : HomeAction
}