package com.music.infinity.presentation.theme

import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

@Composable
fun InfinityTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalInfinityColors provides infinityColors,
        LocalInfinityTypography provides infinityTypography,
        LocalInfinityShapes provides infinityShapes,
        LocalRippleTheme provides InfinityRippleTheme,
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

object InfinityRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor(): Color {
        return InfinityTheme.colors.white40
    }

    @Composable
    override fun rippleAlpha(): RippleAlpha {
        return RippleTheme.defaultRippleAlpha(
            contentColor = InfinityTheme.colors.white40,
            lightTheme = false
        )
    }
}
