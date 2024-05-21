package com.music.infinity.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.music.infinity.R

@Immutable
data class InfinityTypography(
    val l1: TextStyle,
    val l2: TextStyle,
    val l3: TextStyle,
    val b1: TextStyle,
    val b2: TextStyle,
    val b3: TextStyle,
    val t1: TextStyle,
    val t2: TextStyle,
    val t3: TextStyle
)

val LocalInfinityTypography = staticCompositionLocalOf {
    InfinityTypography(
        l1 = TextStyle.Default,
        l2 = TextStyle.Default,
        l3 = TextStyle.Default,
        b1 = TextStyle.Default,
        b2 = TextStyle.Default,
        b3 = TextStyle.Default,
        t1 = TextStyle.Default,
        t2 = TextStyle.Default,
        t3 = TextStyle.Default
    )
}

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val fontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Lato"),
        fontProvider = provider
    )
)

val infinityTypography = InfinityTypography(
    l1 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp
    ),
    l2 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 11.sp
    ),
    l3 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 10.sp
    ),
    b1 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    b2 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    b3 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    t1 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),
    t2 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),
    t3 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    )
)