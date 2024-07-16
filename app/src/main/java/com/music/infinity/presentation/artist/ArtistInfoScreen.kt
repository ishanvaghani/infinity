package com.music.infinity.presentation.artist

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.music.infinity.presentation.artist.composables.AlbumListView
import com.music.infinity.presentation.artist.composables.ArtistInfoView
import com.music.infinity.presentation.artist.composables.ArtistListView
import com.music.infinity.presentation.composables.CircularLoader
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArtistInfoScreen(
    modifier: Modifier,
    viewModel: ArtistInfoViewModel = koinViewModel(),
    artistId: String = "0TnOYISbd1XYRBk9myaseg"
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.loadArtistInfo(artistId)
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    if (uiState.isLoading) {
        CircularLoader(modifier)
    } else {
        Column(modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())) {
            Log.e("TAG", "Data >>>>>>>>>>>>>>>> ${uiState.data?.artistInfo}")
            ArtistInfoView(modifier = Modifier, artistInfo = uiState.data?.artistInfo) {
            }
            Spacer(modifier = Modifier.height(10.dp))
            AlbumListView(
                modifier = Modifier,
                albumList = uiState.data?.artistAlbum ?: ArrayList(),
                uiAction = viewModel::sendAction
            )
            Spacer(modifier = Modifier.height(10.dp))

            ArtistListView(modifier = Modifier, relatedArtist =  uiState.data?.relatedArtist )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}