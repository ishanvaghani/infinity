package com.music.infinity.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.music.infinity.presentation.composables.CircularLoader
import com.music.infinity.presentation.home.composables.AlbumListView
import com.music.infinity.presentation.home.composables.ArtistListView
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(modifier: Modifier, viewModel: HomeViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.isLoading) {
        CircularLoader(Modifier)
    } else {
        Column(modifier = modifier.fillMaxSize()) {
            uiState.data?.artistList?.let {
                ArtistListView(
                    modifier = Modifier,
                    artistList = it,
                    uiAction = viewModel::sendAction
                )
                Spacer(modifier = Modifier.height(36.dp))
            }
            uiState.data?.albumList?.let {
                AlbumListView(
                    modifier = Modifier,
                    albumList = it,
                    uiAction = viewModel::sendAction
                )
                Spacer(modifier = Modifier.height(36.dp))
            }
        }
    }
}