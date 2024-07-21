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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.music.infinity.R
import com.music.infinity.data.local.SharedPrefs
import com.music.infinity.presentation.artist.ArtistInfoScreen
import com.music.infinity.presentation.composables.InfinityAppBar
import com.music.infinity.presentation.genres.GenresScreen
import com.music.infinity.presentation.home.HomeScreen
import com.music.infinity.presentation.models.BottomNavigationItem
import com.music.infinity.presentation.routes.ArtistInfoScreenRoute
import com.music.infinity.presentation.routes.GenresScreenRoute
import com.music.infinity.presentation.routes.HomeScreenRoute
import com.music.infinity.presentation.routes.MainScreenRoute
import com.music.infinity.presentation.routes.SearchScreenRoute
import com.music.infinity.presentation.search.SearchScreen
import com.music.infinity.presentation.theme.InfinityTheme

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

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        androidx.compose.ui.graphics.Color.Transparent
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT)
        )
        setContent {
            InfinityTheme {
                val navController = rememberNavController()
                AppNavHost(navController)
            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = if (SharedPrefs.getSelectedGenres().isNullOrEmpty()) {
            GenresScreenRoute
        } else {
            MainScreenRoute
        }
    ) {
        composable<GenresScreenRoute> {
            GenresScreen(navController)
        }
        composable<MainScreenRoute> {
            MainScreen()
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
fun MainScreen() {
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    val snackBarHostState = remember { SnackbarHostState() }
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = InfinityTheme.colors.codGray,
        bottomBar = {
            BottomNavigationBar(navigationItems, selectedItemIndex, navController) {
                selectedItemIndex = it
            }
        },
        topBar = {
            InfinityAppBar(
                modifier = Modifier,
                showBackIcon = false,
                title = stringResource(R.string.app_name)
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = HomeScreenRoute) {
            composable<HomeScreenRoute> {
                HomeScreen(Modifier.padding(innerPadding), navController)
            }
            composable<SearchScreenRoute> {
                SearchScreen(Modifier.padding(innerPadding))
            }
        }
    }
}