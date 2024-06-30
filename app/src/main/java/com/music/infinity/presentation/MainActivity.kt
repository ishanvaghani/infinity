package com.music.infinity.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.music.infinity.presentation.albums.AlbumsScreen
import com.music.infinity.presentation.home.HomeScreen
import com.music.infinity.presentation.models.BottomNavigationItem
import com.music.infinity.presentation.routes.AlbumsScreenRoute
import com.music.infinity.presentation.routes.HomeScreenRoute
import com.music.infinity.presentation.routes.SearchScreenRoute
import com.music.infinity.presentation.search.SearchScreen
import com.music.infinity.presentation.theme.InfinityTheme

class MainActivity : ComponentActivity() {

    private val navigationItems by lazy {
        listOf(
            BottomNavigationItem(
                "Home",
                Icons.Filled.Home,
                Icons.Outlined.Home,
                HomeScreenRoute
            ),
            BottomNavigationItem(
                "Favourite",
                Icons.Filled.Favorite,
                Icons.Outlined.Favorite,
                HomeScreenRoute
            ),
            BottomNavigationItem(
                "Search",
                Icons.Filled.Search,
                Icons.Outlined.Search,
                SearchScreenRoute
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        androidx.compose.ui.graphics.Color.Transparent
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT)
        )
        setContent {
            InfinityTheme {
                var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
                val navController = rememberNavController()
                val snackBarHostState = remember { SnackbarHostState() }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = InfinityTheme.colors.codGray,
                    bottomBar = {
                        BottomNavigationBar(navigationItems, selectedItemIndex, navController) {
                            selectedItemIndex = it
                        }
                    },
                    snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
                ) { innerPadding ->
                    AppNavHost(Modifier.padding(innerPadding), navController, snackBarHostState)
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    navigationItems: List<BottomNavigationItem>,
    selectedItemIndex: Int,
    navController: NavHostController,
    onSelected: (Int) -> Unit
) {
    NavigationBar(
        containerColor = InfinityTheme.colors.jet,
    ) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    onSelected(index)
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(
                        imageVector = if (selectedItemIndex == index) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.title
                    )
                }
            )
        }
    }
}

@Composable
fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController,
    snackBarHostState: SnackbarHostState
) {
    NavHost(navController = navController, startDestination = HomeScreenRoute) {
        composable<HomeScreenRoute> {
            HomeScreen(modifier, navController)
        }
        composable<SearchScreenRoute> {
            SearchScreen(modifier)
        }
        composable<AlbumsScreenRoute> {
            AlbumsScreen(modifier, snackBarHostState, navController)
        }
    }
}