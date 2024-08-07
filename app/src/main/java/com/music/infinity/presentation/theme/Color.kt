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
private val LimeGreen = Color(0xFF42C83C)
private val Gainsboro = Color(0xFFDBDBDB)
private val Jet = Color(0xFF343434)
private val artistBg = Color(0xFF202930)
private val White40 = Color(0x40FFFFFF)
private val Black = Color(0xFF000000)
private val VampireBlack = Color(0xFF08090C)
private val White80 = Color(0xCCFFFFFF)


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
    val limeGreen: Color,
    val gainsboro: Color,
    val jet: Color,
    val artistBg : Color,
    val white40: Color,
    val black: Color,
    val vampireBlack: Color,
    val white80: Color
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
        limeGreen = Color.Unspecified,
        gainsboro = Color.Unspecified,
        jet = Color.Unspecified,
        artistBg = Color.Unspecified,
        white40 = Color.Unspecified,
        black = Color.Unspecified,
        vampireBlack = Color.Unspecified,
        white80 = Color.Unspecified

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
    limeGreen = LimeGreen,
    gainsboro = Gainsboro,
    jet = Jet,
    artistBg = artistBg,
    white40 = White40,
    black = Black,
    vampireBlack = VampireBlack,
    white80 = White80
)