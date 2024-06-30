package com.music.infinity.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.music.infinity.presentation.theme.InfinityTheme

@Composable
fun InfinityIconButton(
    modifier: Modifier,
    buttonSize: Dp = 28.dp,
    imageVector: ImageVector,
    iconSize: Dp = 20.dp,
    contentDescription: String,
    bgColor: Color = Color.Transparent,
    tint: Color = Color.Unspecified,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .minimumInteractiveComponentSize()
            .size(buttonSize)
            .clip(InfinityTheme.shapes.circle)
            .background(bgColor)
            .clickable(
                onClick = onClick,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = rememberRipple(
                    bounded = false,
                    radius = buttonSize / 2
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(iconSize),
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = tint,
        )
    }
}

@Composable
fun InfinityIconButton(
    modifier: Modifier,
    buttonSize: Dp = 28.dp,
    resourceId: Int,
    resourceSize: Dp = 20.dp,
    contentDescription: String,
    bgColor: Color = Color.Transparent,
    tint: Color = Color.Unspecified,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .minimumInteractiveComponentSize()
            .size(buttonSize)
            .clip(InfinityTheme.shapes.circle)
            .background(bgColor)
            .clickable(
                onClick = onClick,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = rememberRipple(
                    bounded = false,
                    radius = buttonSize / 2
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(resourceSize),
            painter = painterResource(resourceId),
            contentDescription = contentDescription,
            tint = tint,
        )
    }
}