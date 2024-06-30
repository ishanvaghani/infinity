package com.music.infinity.presentation.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.music.infinity.R
import com.music.infinity.presentation.theme.InfinityTheme

@Composable
fun InfinityAppBar(
    modifier: Modifier,
    showBackIcon: Boolean = true,
    backIconColor: Color = InfinityTheme.colors.gainsboro,
    title: String,
    titleColor: Color = InfinityTheme.colors.gainsboro,
    centerTitle: Boolean = true,
    actionIcon: Int? = null,
    actionIconColor: Color? = null,
    actionContentDescription: String? = null,
    onActionClick: (() -> Unit)? = null,
    onIconClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showBackIcon) {
            InfinityIconButton(
                modifier = Modifier,
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                tint = backIconColor,
                contentDescription = stringResource(R.string.back),
                onClick = onIconClick
            )
        }
        Text(
            text = title,
            style = InfinityTheme.typography.b1.copy(
                fontWeight = FontWeight.Medium,
                color = titleColor
            ),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier
                .weight(1f)
                .then(
                    if (showBackIcon.not() && actionIcon == null) {
                        Modifier.padding(vertical = 16.dp)
                    } else {
                        Modifier
                    }
                ),
            textAlign = if (centerTitle) TextAlign.Center else TextAlign.Start
        )
        if (actionIcon != null && onActionClick != null) {
            InfinityIconButton(
                modifier = Modifier,
                resourceId = actionIcon,
                tint = actionIconColor ?: InfinityTheme.colors.gainsboro,
                contentDescription = actionContentDescription ?: "",
                onClick = onActionClick
            )
        } else if (centerTitle) {
            Spacer(modifier = Modifier.size(48.dp))
        }
    }
}

@Preview
@Composable
fun InfinityAppBarPreview(modifier: Modifier = Modifier) {
    InfinityAppBar(
        modifier = Modifier,
        title = "App Bar",
        centerTitle = true,
        showBackIcon = true
    ) {}
}