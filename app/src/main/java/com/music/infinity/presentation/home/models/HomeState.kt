package com.music.infinity.presentation.home.models

import androidx.compose.runtime.Stable
import com.music.infinity.domain.model.AlbumList
import com.music.infinity.domain.model.ArtistList

@Stable
data class HomeState(
    val artistList: ArtistList?,
    val albumList: AlbumList?
)
