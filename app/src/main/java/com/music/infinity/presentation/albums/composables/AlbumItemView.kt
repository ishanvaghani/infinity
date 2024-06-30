package com.music.infinity.presentation.albums.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.music.infinity.R
import com.music.infinity.common.formatDate
import com.music.infinity.common.toArtistsString
import com.music.infinity.domain.model.Album
import com.music.infinity.presentation.albums.models.AlbumsAction
import com.music.infinity.presentation.theme.InfinityTheme

@Composable
fun AlbumItemView(
    modifier: Modifier,
    album: Album,
    uiAction: (AlbumsAction) -> Unit
) {
    val artists = remember {
        album.artists.toArtistsString()
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                uiAction(AlbumsAction.AlbumClick(album))
            }
            .padding(horizontal =  16.dp, vertical = 8.dp)
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
                .width(100.dp)
                .height(120.dp)
                .clip(InfinityTheme.shapes.rc30)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = album.name,
                style = InfinityTheme.typography.b1.copy(
                    color = InfinityTheme.colors.gainsboro
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = artists,
                style = InfinityTheme.typography.b2.copy(
                    color = InfinityTheme.colors.silverChalice
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Released on ${album.releaseDate.formatDate()}",
                style = InfinityTheme.typography.b3.copy(
                    color = InfinityTheme.colors.silverChalice
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${album.totalTracks} songs",
                style = InfinityTheme.typography.b3.copy(
                    color = InfinityTheme.colors.silverChalice
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}