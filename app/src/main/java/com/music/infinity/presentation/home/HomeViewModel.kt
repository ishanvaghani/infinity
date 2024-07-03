package com.music.infinity.presentation.home

import androidx.lifecycle.viewModelScope
import com.music.infinity.common.launchIO
import com.music.infinity.data.local.SharedPrefs
import com.music.infinity.domain.usecase.AlbumUseCase
import com.music.infinity.domain.usecase.PlaylistUseCase
import com.music.infinity.domain.usecase.RecommendationUseCase
import com.music.infinity.presentation.base.BaseViewModel
import com.music.infinity.presentation.home.models.HomeAction
import com.music.infinity.presentation.home.models.HomeState
import com.music.infinity.presentation.models.ScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async

class HomeViewModel(
    private val albumUseCase: AlbumUseCase,
    private val playlistUseCase: PlaylistUseCase,
    private val recommendationUseCase: RecommendationUseCase
) : BaseViewModel<HomeState, HomeAction>() {

    init {
        loadData()
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("Handle $exception in CoroutineExceptionHandler")
    }

    override fun getInitialState(): ScreenState<HomeState> {
        return ScreenState(true)
    }

    private fun loadData() {
        viewModelScope.launchIO { scope ->
            val albumsListResponse = scope.async { albumUseCase.getNewReleasesAlbums() }
            val playlistListResponse = scope.async { playlistUseCase.getFeaturedPlaylists() }
            val recommendationListResponse = scope.async {
                recommendationUseCase.getRecommendations(SharedPrefs.getSelectedGenres())
            }

            val albumsList = albumsListResponse.await()
            val playlistList = playlistListResponse.await()
            val recommendationList = recommendationListResponse.await()

            setDataState(
                HomeState(
                    playlistList = playlistList.getOrNull(),
                    albumList = albumsList.getOrNull(),
                    recommendationList = recommendationList.getOrNull()
                )
            )
        }
    }
}