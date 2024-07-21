package com.music.infinity.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.music.infinity.presentation.composables.CircularLoader
import com.music.infinity.presentation.theme.InfinityTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    modifier: Modifier,
    viewModel: SearchViewModel = koinViewModel()
) {

    val textSearched = remember { mutableStateOf(TextFieldValue()) }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    if (uiState.isLoading) {
        CircularLoader(modifier)
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
                .verticalScroll(scrollState)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        InfinityTheme.colors.doveGray,
                        RoundedCornerShape(20.dp)
                    )
            ) {
                Row(
                    modifier = Modifier
                        .background(color = Color.Transparent)
                ) {

                    TextField(
                        value = textSearched.value,
                        onValueChange = {
                            textSearched.value = it
                            viewModel.searchMusics(textSearched.value.text, )
                        },
                        placeholder = {
                            Text(text = "Search album, artist or song")
                        },
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 17.sp
                        ),

                        maxLines = 1,
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent)
                    )

                }
            }
        }
    }
}