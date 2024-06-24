package com.music.infinity.presentation.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.music.infinity.R
import com.music.infinity.common.model.Image
import com.music.infinity.domain.model.Artist
import com.music.infinity.domain.model.ArtistList
import com.music.infinity.presentation.composables.ArtistItemView
import com.music.infinity.presentation.home.models.HomeAction
import com.music.infinity.presentation.theme.InfinityTheme

@Composable
fun ArtistListView(
    modifier: Modifier,
    artistList: ArtistList,
    uiAction: (HomeAction) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.artists),
                style = InfinityTheme.typography.t1.copy(
                    color = InfinityTheme.colors.gainsboro,
                    fontWeight = FontWeight.Bold
                )
            )
            TextButton(
                onClick = { uiAction(HomeAction.MoreArtistsClick) },
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
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 14.dp)
        ) {
            items(artistList.artists) {
                ArtistItemView(Modifier, it, uiAction)
            }
        }
    }
}

@Preview
@Composable
private fun ArtistListViewPreview() {
    ArtistListView(
        modifier = Modifier,
        artistList = ArtistList(
            artists = listOf(
                Artist(
                    "",
                    "",
                    "",
                    "",
                    "",
                    listOf(Image(100, 100, "https://picsum.photos/id/237/200/300"))
                )
            ),
            limit = 0,
            offset = 0,
            total = 0
        ),
        uiAction = {}
    )
}