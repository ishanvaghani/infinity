import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.music.infinity.presentation.theme.InfinityTheme

@Composable
fun ArtistCountInfo(
    modifier: Modifier,
    title : String,
    count : String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = count,
            style = InfinityTheme.typography.t3.copy(
                color = InfinityTheme.colors.mercury,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            maxLines = 1,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = title,
            style = InfinityTheme.typography.t3.copy(
                color = InfinityTheme.colors.mercury.copy(alpha = 0.5f),
                fontWeight = FontWeight.Thin,
                fontSize = 12.sp,
            ),
            maxLines = 1,
        )
    }
}