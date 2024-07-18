package com.music.infinity.presentation.home.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.music.infinity.R
import com.music.infinity.domain.model.PlaylistList
import com.music.infinity.presentation.home.models.HomeAction
import com.music.infinity.presentation.theme.InfinityTheme

@Composable
fun PlaylistListView(
    modifier: Modifier,
    playlistList: PlaylistList,
    uiAction: (HomeAction) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.featured_playlists),
            style = InfinityTheme.typography.t2.copy(
                color = InfinityTheme.colors.gainsboro,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(playlistList.playlists, key = { it.id }) {
                PlaylistItemView(
                    modifier = Modifier,
                    playlist = it,
                    uiAction = uiAction
                )
            }
        }
    }
}