package com.music.infinity.presentation.artist.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.music.infinity.R
import com.music.infinity.domain.model.ArtistAlbum
import com.music.infinity.presentation.artist.models.ArtistInfoAction
import com.music.infinity.presentation.theme.InfinityTheme

@Composable
fun AlbumListView(modifier: Modifier, albumList: List<ArtistAlbum>, uiAction: (ArtistInfoAction) -> Unit) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.albums),
                style = InfinityTheme.typography.t1.copy(
                    color = InfinityTheme.colors.gainsboro,
                    fontWeight = FontWeight.Bold
                )
            )
            TextButton(
                onClick = { },
                content = {
                    Text(
                        text = stringResource(R.string.more),
                        style = InfinityTheme.typography.t3.copy(
                            color = InfinityTheme.colors.limeGreen,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 14.dp)
        ) {
            itemsIndexed(albumList) { index, item ->
                ArtistAlbumItemView(
                    modifier = Modifier,
                    album = item,
                    addEndMargin = index < albumList.size - 1,
                    uiAction = uiAction
                )
            }
        }
    }
}
