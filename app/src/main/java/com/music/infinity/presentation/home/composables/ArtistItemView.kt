package com.music.infinity.presentation.home.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.music.infinity.R
import com.music.infinity.common.model.Image
import com.music.infinity.domain.model.Artist
import com.music.infinity.presentation.home.models.HomeAction
import com.music.infinity.presentation.theme.InfinityTheme

@Composable
fun ArtistItemView(modifier: Modifier, artist: Artist, uiAction: (HomeAction) -> Unit) {
    Column(modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(artist.images?.first()?.url)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.artist_image),
            placeholder = rememberAsyncImagePainter(R.drawable.ic_launcher_foreground),
            fallback = rememberAsyncImagePainter(R.drawable.ic_launcher_foreground),
            modifier = Modifier
                .height(150.dp)
                .width(150.dp)
                .clip(InfinityTheme.shapes.circle)
                .clickable {
                    uiAction(HomeAction.ArtistClick(artist))
                }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = artist.name,
            style = InfinityTheme.typography.t3.copy(
                color = InfinityTheme.colors.mercury,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

@Preview
@Composable
private fun ArtistItemViewPreview() {
    ArtistItemView(
        modifier = Modifier,
        artist = Artist(
            "",
            "",
            "",
            "",
            "",
            listOf(Image(100, 100, "https://picsum.photos/id/237/200/300"))
        ),
        uiAction = {}
    )
}