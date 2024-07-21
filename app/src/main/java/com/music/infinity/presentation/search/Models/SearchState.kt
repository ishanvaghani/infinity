package com.music.infinity.presentation.search.Models

import androidx.compose.runtime.Stable
import com.music.infinity.domain.model.AlbumList
import com.music.infinity.domain.model.PlaylistList
import com.music.infinity.domain.model.RecommendationList
import com.music.infinity.domain.model.SearchList

@Stable
data class SearchState(
    val searchList : SearchList?
)