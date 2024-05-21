package com.music.infinity.presentation.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Immutable
data class InfinityShapes(
    val rc4: Shape,
    val rc8: Shape,
    val rc12: Shape,
    val rc16: Shape,
    val rc20: Shape,
    val rc24: Shape,
    val rc30: Shape,
    val circle: Shape
)

val LocalInfinityShapes = staticCompositionLocalOf {
    InfinityShapes(
        rc4 = RoundedCornerShape(ZeroCornerSize),
        rc8 = RoundedCornerShape(ZeroCornerSize),
        rc12 = RoundedCornerShape(ZeroCornerSize),
        rc16 = RoundedCornerShape(ZeroCornerSize),
        rc20 = RoundedCornerShape(ZeroCornerSize),
        rc24 = RoundedCornerShape(ZeroCornerSize),
        rc30 = RoundedCornerShape(ZeroCornerSize),
        circle = CircleShape
    )
}

val infinityShapes = InfinityShapes(
    rc4 = RoundedCornerShape(4.dp),
    rc8 = RoundedCornerShape(8.dp),
    rc12 = RoundedCornerShape(12.dp),
    rc16 = RoundedCornerShape(16.dp),
    rc20 = RoundedCornerShape(20.dp),
    rc24 = RoundedCornerShape(24.dp),
    rc30 = RoundedCornerShape(30.dp),
    circle = CircleShape
)