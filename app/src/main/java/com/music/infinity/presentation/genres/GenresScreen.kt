package com.music.infinity.presentation.genres

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.music.infinity.R
import com.music.infinity.presentation.composables.CircularLoader
import com.music.infinity.presentation.composables.InfinityAppBar
import com.music.infinity.presentation.composables.InfinityButton
import com.music.infinity.presentation.genres.composables.GenreItem
import com.music.infinity.presentation.genres.models.GenresAction
import com.music.infinity.presentation.genres.models.GenresState
import com.music.infinity.presentation.models.ScreenState
import com.music.infinity.presentation.routes.HomeScreenRoute
import com.music.infinity.presentation.routes.MainScreenRoute
import com.music.infinity.presentation.theme.InfinityTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun GenresScreen(navController: NavHostController, viewModel: GenresViewModel = koinViewModel()) {
    LaunchedEffect(key1 = Unit) {
        viewModel.uiAction.collect {
            when (it) {
                is GenresAction.GenreClick -> viewModel.genreClick(it.genre)
                GenresAction.ContinueClick -> {
                    viewModel.saveGenres()
                    navController.navigate(MainScreenRoute)
                }
            }
        }
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            InfinityAppBar(
                modifier = Modifier,
                showBackIcon = false,
                title = stringResource(R.string.choose_genres),
                titleColor = InfinityTheme.colors.concrete,
            )
        },
        containerColor = InfinityTheme.colors.codGray,
    ) { paddingValues ->
        if (uiState.isLoading) {
            CircularLoader(modifier = Modifier.padding(paddingValues))
        } else {
            Column(modifier = Modifier.padding(paddingValues)) {
                GenresList(
                    modifier = Modifier.weight(1f),
                    uiState = uiState,
                    isSelected = {
                        viewModel.isSelected(it)
                    },
                    onSelect = {
                        viewModel.sendAction(GenresAction.GenreClick(it))
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                InfinityButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    text = stringResource(R.string.continue_btn),
                    textStyle = InfinityTheme.typography.b2.copy(
                        color = InfinityTheme.colors.concrete,
                        fontWeight = FontWeight.SemiBold
                    ),
                    onClick = {
                        viewModel.sendAction(GenresAction.ContinueClick)
                    }
                )
            }
        }
    }
}

@Composable
fun GenresList(
    modifier: Modifier,
    uiState: ScreenState<GenresState>,
    isSelected: (genre: String) -> Boolean,
    onSelect: (genre: String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = PaddingValues(8.dp)
    ) {
        items(uiState.data?.genres ?: listOf()) { genre ->
            GenreItem(
                modifier = Modifier,
                genre = genre,
                isSelected = isSelected(genre),
                onSelect = onSelect
            )
        }
    }
}