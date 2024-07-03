package com.music.infinity.presentation.composables

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.music.infinity.presentation.theme.InfinityTheme

@Composable
fun InfinitySnackBar(data: SnackbarData) {
    return Snackbar(
        snackbarData = data,
        modifier = Modifier,
        actionColor = InfinityTheme.colors.limeGreen,
        containerColor = InfinityTheme.colors.white80,
        contentColor = InfinityTheme.colors.vampireBlack
    )
}