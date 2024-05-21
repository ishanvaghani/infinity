package com.music.infinity.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun InfinityTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalInfinityColors provides infinityColors,
        LocalInfinityTypography provides infinityTypography,
        LocalInfinityShapes provides infinityShapes,
        content = content
    )
}

object InfinityTheme {
    val colors: InfinityColors
        @Composable
        get() = LocalInfinityColors.current

    val typography: InfinityTypography
        @Composable
        get() = LocalInfinityTypography.current

    val shapes: InfinityShapes
        @Composable
        get() = LocalInfinityShapes.current
}