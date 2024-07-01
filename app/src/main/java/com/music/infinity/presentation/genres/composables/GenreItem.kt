package com.music.infinity.presentation.genres.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.music.infinity.presentation.theme.InfinityTheme

@Composable
fun GenreItem(
    modifier: Modifier,
    genre: String,
    isSelected: Boolean,
    onSelect: (genre: String) -> Unit
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(50.dp)
            .background(color = InfinityTheme.colors.jet, shape = InfinityTheme.shapes.rc8)
            .then(
                if (isSelected) {
                    Modifier.border(
                        width = 1.5.dp,
                        color = InfinityTheme.colors.limeGreen,
                        shape = InfinityTheme.shapes.rc8
                    )
                } else {
                    Modifier
                }
            )
            .clickable {
                onSelect(genre)
            }
    ) {
        Text(
            text = genre,
            style = InfinityTheme.typography.b2.copy(
                color = InfinityTheme.colors.gainsboro
            ),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun GenreItemPreview() {
    GenreItem(
        modifier = Modifier,
        genre = "Drama",
        isSelected = false,
        onSelect = {}
    )
}