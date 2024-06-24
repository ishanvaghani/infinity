package com.music.infinity.presentation.home.models

import androidx.compose.runtime.Stable
import com.music.infinity.domain.model.Album
import com.music.infinity.domain.model.Artist

@Stable
sealed interface HomeAction {
    data object MoreArtistsClick : HomeAction
    data class ArtistClick(val artist: Artist) : HomeAction
    data object MoreAlbumsClick : HomeAction
    data class AlbumClick(val album: Album) : HomeAction
}