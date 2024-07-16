package com.music.infinity.presentation.artist

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.music.infinity.domain.usecase.ArtistUseCase
import com.music.infinity.presentation.artist.models.ArtistInfoAction
import com.music.infinity.presentation.artist.models.ArtistInfoState
import com.music.infinity.presentation.base.BaseViewModel
import com.music.infinity.presentation.home.models.HomeState
import com.music.infinity.presentation.models.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ArtistInfoViewModel(
    private val artistUseCase: ArtistUseCase
) : BaseViewModel<ArtistInfoState, ArtistInfoAction>() {

    override fun getInitialState(): ScreenState<ArtistInfoState> {
        return ScreenState(true)
    }

    fun loadArtistInfo(artistId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val artistinforesponse = this.async { artistUseCase.getArtistInfo(artistId) }
            val artistAlbumResponse =
                this.async { artistUseCase.getArtistAlbum(artistId) }
            val relatedArtistResponse =
                this.async { artistUseCase.getRelatedArtist(artistId) }

            val artistInfo = artistinforesponse.await()
            val artistAlbum = artistAlbumResponse.await()
            val relatedArtist = relatedArtistResponse.await()



            setScreenState(
                getCurrentState().copy(
                    isLoading = false,
                    data = ArtistInfoState(
                        artistInfo.getOrNull(),
                        artistAlbum.getOrNull(),
                        relatedArtist.getOrNull()
                    )
                )
            )
        }
    }

}
