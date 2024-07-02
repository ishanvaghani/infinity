package com.music.infinity.presentation.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.music.infinity.R
import com.music.infinity.common.formatDuration
import com.music.infinity.domain.model.Track
import com.music.infinity.presentation.home.models.HomeAction
import com.music.infinity.presentation.theme.InfinityTheme

@Composable
fun TrackItemView(modifier: Modifier, track: Track, uiAction: (HomeAction) -> Unit) {
    Column(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = {
                    uiAction(HomeAction.TrackClick(track))
                }
            )
            .padding(8.dp)
            .width(147.dp)
    ) {
        Box(
            modifier = Modifier
                .height(185.dp)
                .clip(InfinityTheme.shapes.rc20)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(track.album.images.first().url)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.artist_image),
                placeholder = rememberAsyncImagePainter(R.drawable.ic_launcher_foreground),
                fallback = rememberAsyncImagePainter(R.drawable.ic_launcher_foreground),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(InfinityTheme.colors.black.copy(alpha = 0.3f))
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = stringResource(R.string.play),
                    tint = InfinityTheme.colors.dustyGray,
                    modifier = Modifier
                        .size(50.dp)
                        .background(
                            color = InfinityTheme.colors.mineShaft,
                            shape = InfinityTheme.shapes.circle
                        )
                        .padding(8.dp)
                        .align(Alignment.Center)

                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = track.name,
            style = InfinityTheme.typography.t3.copy(
                color = InfinityTheme.colors.mercury,
                fontWeight = FontWeight.Bold
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = formatDuration(track.durationMs),
            style = InfinityTheme.typography.b2.copy(
                color = InfinityTheme.colors.mercury
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}