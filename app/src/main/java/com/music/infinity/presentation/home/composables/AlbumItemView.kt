package com.music.infinity.presentation.home.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.music.infinity.domain.model.Album
import com.music.infinity.presentation.home.models.HomeAction
import com.music.infinity.presentation.theme.InfinityTheme

@Composable
fun AlbumItemView(
    modifier: Modifier,
    album: Album,
    uiAction: (HomeAction) -> Unit
) {
    Column(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = {
                    uiAction(HomeAction.AlbumClick(album))
                }
            )
            .padding(8.dp)
            .width(147.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(album.images.first().url)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.artist_image),
            placeholder = rememberAsyncImagePainter(R.drawable.ic_launcher_foreground),
            fallback = rememberAsyncImagePainter(R.drawable.ic_launcher_foreground),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(185.dp)
                .clip(InfinityTheme.shapes.rc30)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = album.name,
            style = InfinityTheme.typography.t3.copy(
                color = InfinityTheme.colors.mercury,
                fontWeight = FontWeight.Bold
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
private fun AlbumItemViewPreview() {
    AlbumItemView(
        modifier = Modifier,
        album = Album(
            "",
            emptyList(),
            "",
            listOf(Image(100, 100, "https://picsum.photos/id/237/200/300")),
            "",
            "",
            0
        ),
        uiAction = {}
    )
}