package com.music.infinity.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val Concrete = Color(0xFFF2F2F2)
private val SilverChalice = Color(0xFFA0A0A0)
private val Alto = Color(0xFFD7D7D7)
private val White = Color.White
private val DustyGray = Color(0xFF959595)
private val Mercury = Color(0xFFE1E1E1)
private val DoveGray = Color(0xFF616161)
private val MineShaft = Color(0xFF2C2C2C)
private val CodGray = Color(0xFF1C1B1B)
private val SunsetPurple = Color(0xFFA865B5)

@Immutable
data class InfinityColors(
    val concrete: Color,
    val silverChalice: Color,
    val alto: Color,
    val white: Color,
    val dustyGray: Color,
    val mercury: Color,
    val doveGray: Color,
    val mineShaft: Color,
    val codGray: Color,
    val sunsetPurple: Color
)

val LocalInfinityColors = staticCompositionLocalOf {
    InfinityColors(
        concrete = Color.Unspecified,
        silverChalice = Color.Unspecified,
        alto = Color.Unspecified,
        white = Color.Unspecified,
        dustyGray = Color.Unspecified,
        mercury = Color.Unspecified,
        doveGray = Color.Unspecified,
        mineShaft = Color.Unspecified,
        codGray = Color.Unspecified,
        sunsetPurple = Color.Unspecified
    )
}

val infinityColors = InfinityColors(
    concrete = Concrete,
    silverChalice = SilverChalice,
    alto = Alto,
    white = White,
    dustyGray = DustyGray,
    mercury = Mercury,
    doveGray = DoveGray,
    mineShaft = MineShaft,
    codGray = CodGray,
    sunsetPurple = SunsetPurple
)