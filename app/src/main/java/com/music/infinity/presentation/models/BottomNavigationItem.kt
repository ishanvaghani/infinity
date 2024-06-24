package com.music.infinity.presentation.models

import androidx.compose.ui.graphics.vector.ImageVector
import com.music.infinity.presentation.routes.ScreenRoute

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: ScreenRoute
)
