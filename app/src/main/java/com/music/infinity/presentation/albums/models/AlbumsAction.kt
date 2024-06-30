package com.music.infinity.presentation.albums.models

import androidx.compose.runtime.Stable
import com.music.infinity.domain.model.Album

@Stable
sealed interface AlbumsAction {
    data object Back : AlbumsAction
    data class AlbumClick(val album: Album) : AlbumsAction
}