package com.music.infinity.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.music.infinity.presentation.composables.CircularLoader
import com.music.infinity.presentation.home.composables.AlbumListView
import com.music.infinity.presentation.home.composables.PlaylistListView
import com.music.infinity.presentation.home.composables.TrackListView
import com.music.infinity.presentation.home.models.HomeAction
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.uiAction.collect {
            when (it) {
                is HomeAction.AlbumClick -> {}
                is HomeAction.PlaylistClick -> {}
                is HomeAction.TrackClick -> {}
                is HomeAction.PlaylistPlayClick -> {}
            }
        }
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    if (uiState.isLoading) {
        CircularLoader(modifier)
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            uiState.data?.recommendationList?.let {
                TrackListView(
                    modifier = Modifier,
                    recommendationList = it,
                    uiAction = viewModel::sendAction
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            uiState.data?.playlistList?.let {
                PlaylistListView(
                    modifier = Modifier,
                    playlistList = it,
                    uiAction = viewModel::sendAction
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            uiState.data?.albumList?.let {
                AlbumListView(
                    modifier = Modifier,
                    albumList = it,
                    uiAction = viewModel::sendAction
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}