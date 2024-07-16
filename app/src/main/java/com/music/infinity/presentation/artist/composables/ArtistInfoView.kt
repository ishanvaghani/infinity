package com.music.infinity.presentation.artist.composables

import ArtistCountInfo
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.size.Size
import com.music.infinity.R
import com.music.infinity.common.model.Image
import com.music.infinity.data.remote.dto.ExternalUrlsDto
import com.music.infinity.domain.model.Album
import com.music.infinity.domain.model.AlbumList
import com.music.infinity.domain.model.ArtistInfo
import com.music.infinity.domain.model.ExternalUrls
import com.music.infinity.domain.model.Followers
import com.music.infinity.presentation.composables.AlbumItemView
import com.music.infinity.presentation.home.models.HomeAction
import com.music.infinity.presentation.theme.InfinityTheme

@Composable
fun ArtistInfoView(modifier: Modifier, artistInfo: ArtistInfo?, uiAction: (HomeAction) -> Unit) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                InfinityTheme.colors.artistBg,
                RoundedCornerShape(bottomEnd = 75.dp, bottomStart = 75.dp)
            )
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(artistInfo?.images?.get(0)?.url)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.artist_image),
                placeholder = rememberAsyncImagePainter(R.drawable.ic_launcher_foreground),
                fallback = rememberAsyncImagePainter(R.drawable.ic_launcher_foreground),
                modifier = Modifier
                    .size(125.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = artistInfo?.name ?: "",
                color = InfinityTheme.colors.white,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
            ) {
                ArtistCountInfo(
                    modifier = modifier,
                    title = stringResource(R.string.followers),
                    count = artistInfo?.followers?.total.toString()
                )
                Spacer(modifier = Modifier.width(20.dp))
                Box(
                    modifier = modifier
                        .height(20.dp)
                        .width(1.dp)
                        .background(
                            color = InfinityTheme.colors.mercury.copy(alpha = 0.5f),
                        ).align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width(20.dp))
                ArtistCountInfo(
                    modifier = modifier,
                    title = stringResource(R.string.popularity),
                    count = artistInfo?.popularity.toString()
                )
            }

            Spacer(modifier = Modifier.height(30.dp))


        }
    }

}

@Preview
@Composable
private fun AlbumListViewPreview() {
    ArtistInfoView(
        modifier = Modifier,
        artistInfo = ArtistInfo(
            href = "",
            id = "",
            name = "Pitbull",
            followers = Followers(
                "", 1212L
            ),
            type = "",
            uri = "",
            genres = arrayListOf(""),
            externalUrls = ExternalUrls(""),
            popularity = 0,
            images = arrayListOf(
                Image(
                    url = "",
                    width = 100,
                    height = 100
                )
            )
        ),
        uiAction = {}
    )
}