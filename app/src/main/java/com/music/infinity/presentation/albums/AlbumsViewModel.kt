package com.music.infinity.presentation.albums

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.music.infinity.domain.model.Album
import com.music.infinity.domain.usecase.AlbumUseCase
import com.music.infinity.presentation.albums.models.AlbumsAction
import com.music.infinity.presentation.base.BaseViewModel
import com.music.infinity.presentation.models.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AlbumsViewModel(
    private val albumUseCase: AlbumUseCase
) : BaseViewModel<Nothing, AlbumsAction>() {

    init {
        loadAlbums()
    }

    private val _albums = MutableStateFlow<PagingData<Album>>(PagingData.empty())
    val albums = _albums.asStateFlow()

    override fun getInitialState(): ScreenState<Nothing> {
        return ScreenState(true)
    }

    private fun loadAlbums() {
        viewModelScope.launch(Dispatchers.IO) {
            albumUseCase.getNewReleasesAlbumsPaging().cachedIn(viewModelScope).collect {
                _albums.value = it
            }
        }
    }
}