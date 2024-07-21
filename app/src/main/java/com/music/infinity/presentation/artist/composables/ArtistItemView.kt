import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.music.infinity.R
import com.music.infinity.domain.model.Artist
import com.music.infinity.domain.model.ArtistInfo
import com.music.infinity.presentation.artist.models.ArtistInfoAction
import com.music.infinity.presentation.theme.InfinityTheme

@Composable
fun ArtistItemView(modifier: Modifier, artist: ArtistInfo, addEndMargin: Boolean, uiAction : (ArtistInfoAction) -> Unit) {
    Column(
        modifier = modifier
            .clickable(
                onClick = {
                    uiAction(ArtistInfoAction.ArtistClick(artist))
                }
            )
            .then(
                if (addEndMargin) Modifier.padding(end = 16.dp) else Modifier
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(artist.images?.get(0)?.url)
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
            text = artist.name ?: "",
            color = InfinityTheme.colors.white,
            style = InfinityTheme.typography.t3.copy(
                color = InfinityTheme.colors.mercury,
                fontWeight = FontWeight.W300
            ),
        )

    }
}