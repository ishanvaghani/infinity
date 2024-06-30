package com.music.infinity.presentation.albums

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.music.infinity.R
import com.music.infinity.domain.model.Album
import com.music.infinity.presentation.albums.composables.AlbumItemView
import com.music.infinity.presentation.albums.models.AlbumsAction
import com.music.infinity.presentation.composables.CircularLoader
import com.music.infinity.presentation.composables.InfinityAppBar
import com.music.infinity.presentation.theme.InfinityTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun AlbumsScreen(
    modifier: Modifier,
    snackBarHostState: SnackbarHostState,
    navController: NavHostController,
    viewModel: AlbumsViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.uiAction.collect {
            when (it) {
                is AlbumsAction.AlbumClick -> TODO()
                AlbumsAction.Back -> navController.popBackStack()
            }
        }
    }

    val albumsData = viewModel.albums.collectAsLazyPagingItems()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = InfinityTheme.colors.codGray,
        topBar = {
            InfinityAppBar(
                modifier = Modifier,
                title = stringResource(R.string.new_released_albums),
                onIconClick = {
                    viewModel.sendAction(AlbumsAction.Back)
                }
            )
        }
    ) { padding ->
        when (albumsData.loadState.refresh) {
            is LoadState.Error -> {
                val errorMessage = stringResource(R.string.something_went_wrong)
                val retryMessage = stringResource(R.string.retry)
                LaunchedEffect(Unit) {
                    val result = snackBarHostState.showSnackbar(errorMessage, retryMessage)
                    if (result == SnackbarResult.ActionPerformed) {
                        albumsData.retry()
                    }
                }
            }

            LoadState.Loading -> {
                CircularLoader(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                )
            }

            is LoadState.NotLoading -> {
                AlbumsList(
                    modifier = Modifier.padding(padding),
                    albumsData = albumsData,
                    uiAction = viewModel::sendAction
                )
            }
        }
    }
}

@Composable
fun AlbumsList(
    modifier: Modifier,
    albumsData: LazyPagingItems<Album>,
    uiAction: (AlbumsAction) -> Unit
) {
    LazyColumn(modifier = modifier) {
        when (albumsData.loadState.prepend) {
            is LoadState.Loading -> {
                item {
                    CircularLoader(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    )
                }
            }

            else -> {}
        }
        items(albumsData.itemCount) {
            albumsData[it]?.let { album ->
                AlbumItemView(
                    modifier = Modifier.animateItem(),
                    album = album,
                    uiAction = uiAction
                )
            }
        }
        when (albumsData.loadState.append) {
            is LoadState.Loading -> {
                item {
                    CircularLoader(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    )
                }
            }

            else -> {}
        }
    }
}