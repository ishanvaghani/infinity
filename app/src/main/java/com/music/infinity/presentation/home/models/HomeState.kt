package com.music.infinity.presentation.home.models

import androidx.compose.runtime.Stable
import com.music.infinity.domain.model.AlbumList
import com.music.infinity.domain.model.ArtistList
import com.music.infinity.domain.model.PlaylistList
import com.music.infinity.domain.model.RecommendationList

@Stable
data class HomeState(
    val playlistList: PlaylistList?,
    val albumList: AlbumList?,
    val recommendationList: RecommendationList?
)
