package com.music.infinity.presentation.home.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.music.infinity.R
import com.music.infinity.common.model.Image
import com.music.infinity.domain.model.Playlist
import com.music.infinity.presentation.composables.InfinityIconButton
import com.music.infinity.presentation.home.models.HomeAction
import com.music.infinity.presentation.theme.InfinityTheme

@Composable
fun PlaylistItemView(modifier: Modifier, playlist: Playlist, uiAction: (HomeAction) -> Unit) {
    Column(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = {
                    uiAction(HomeAction.PlaylistClick(playlist))
                }
            )
            .padding(8.dp)
            .width(156.dp)
    ) {
        Box(
            modifier = Modifier
                .height(190.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(playlist.images.first().url)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.artist_image),
                placeholder = rememberAsyncImagePainter(R.drawable.ic_launcher_foreground),
                fallback = rememberAsyncImagePainter(R.drawable.ic_launcher_foreground),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .height(166.dp)
                    .width(132.dp)
                    .clip(InfinityTheme.shapes.rc20)
            )
            InfinityIconButton(
                modifier = Modifier.align(Alignment.BottomEnd),
                imageVector = Icons.Filled.PlayArrow,
                tint = InfinityTheme.colors.dustyGray,
                contentDescription = stringResource(R.string.play),
                bgColor = InfinityTheme.colors.mineShaft,
                onClick = {
                    uiAction(HomeAction.PlaylistPlayClick(playlist))
                }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = playlist.name,
            style = InfinityTheme.typography.t3.copy(
                color = InfinityTheme.colors.mercury,
                fontWeight = FontWeight.Bold
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = playlist.description,
            style = InfinityTheme.typography.b2.copy(
                color = InfinityTheme.colors.mercury
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
fun PlaylistItemViewPreview() {
    PlaylistItemView(
        modifier = Modifier,
        playlist = Playlist(
            description = "This is description",
            href = "",
            id = "1",
            images = listOf(
                Image(100, 100, "https://picsum.photos/id/237/200/300")
            ),
            name = "This is name",
        ),
        uiAction = {}
    )
}