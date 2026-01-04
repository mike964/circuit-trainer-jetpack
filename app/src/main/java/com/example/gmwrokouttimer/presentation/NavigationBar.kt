package com.example.gmwrokouttimer.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gmwrokouttimer.R
import com.example.gmwrokouttimer.navigation.Screen
import com.example.gmwrokouttimer.presentation.screens.ProfileScreen
import com.example.gmwrokouttimer.presentation.screens.SearchScreen

data class NavItemState(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

// # Bottom Navigation Bar
@Composable
fun NavigationBar(
    appViewModel: AppViewModel,
    noteViewModel: NoteViewModel,
) {
    val navBarItems = listOf(
        NavItemState(
            "Main",
            "main_screen",
            Icons.Filled.Place, Icons.Outlined.Place
        ),
        NavItemState(
            "Room",
            "room_screen",
            Icons.Filled.Menu, Icons.Outlined.Menu
        ),
        NavItemState(
            "Search",
            "search_screen",
            Icons.Filled.Search, Icons.Outlined.Search
        ),
        NavItemState(
            "Profile",
            "profile_screen",
            Icons.Filled.Person, Icons.Outlined.Person
        ),
    )
    var navBarState by rememberSaveable {
        mutableIntStateOf(0)
    }
    val navController = rememberNavController()
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                modifier = Modifier.fillMaxWidth(),
                containerColor = MaterialTheme.colorScheme.background,
                tonalElevation = 10.dp
            ) {
                navBarItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = navBarState == index,
                        onClick = {
                            navBarState = index
                            navController.navigate(item.route)
                        },
                        icon = {
                            Icon(
                                imageVector = if (navBarState == index) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title,
                            )
                        },
                        label = { Text(item.title) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.background
                        ),
                    )
                }
            }
        }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Main.route,
            modifier = Modifier.padding(paddingValues = innerPadding)
        ) {
            composable(Screen.Main.route) {
                MainScreen(
                    viewModel = appViewModel,
                    navController = navController
                )
            }
            composable(Screen.Room.route) {
                NoteScreen(vm = noteViewModel, navController = navController)
            }
            composable(Screen.Search.route) {
                SearchScreen(navController = navController)
            }
            composable(Screen.Profile.route) {
                ProfileScreen(navController = navController)
            }
        }
    }
}
