package com.music.infinity.presentation.home

import androidx.lifecycle.viewModelScope
import com.music.infinity.domain.usecase.AlbumUseCase
import com.music.infinity.presentation.base.BaseViewModel
import com.music.infinity.presentation.home.models.HomeAction
import com.music.infinity.presentation.home.models.HomeState
import com.music.infinity.presentation.models.ScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeViewModel(
    private val albumUseCase: AlbumUseCase
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
        viewModelScope.launch(Dispatchers.IO) {
            val albumsListResponse = this.async { albumUseCase.getNewReleasesAlbums() }

            val albumsList = albumsListResponse.await()

            setScreenState(
                getCurrentState().copy(
                    isLoading = false,
                    data = HomeState(
                        artistList = null,
                        albumList = albumsList.getOrNull()
                    )
                )
            )
        }
    }
}