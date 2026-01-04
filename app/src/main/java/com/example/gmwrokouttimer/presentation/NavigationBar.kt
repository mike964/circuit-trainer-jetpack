package com.example.gmwrokouttimer.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gmwrokouttimer.navigation.Screen
import com.example.gmwrokouttimer.navigation.navBarItems
import com.example.gmwrokouttimer.presentation.screens.ProfileScreen
import com.example.gmwrokouttimer.presentation.screens.SearchScreen
import com.example.gmwrokouttimer.ui.theme.GMWrokoutTimerTheme


// # Bottom Navigation Bar
@Composable
fun NavigationBar(
    appViewModel: AppViewModel,
    noteViewModel: NoteViewModel,
) {

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
//                containerColor = Color(0xFF6B5991),
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
                                painter = painterResource(id = item.selectedIcon),
                                contentDescription = item.title,
                                tint = Color.DarkGray
                            )
//                            Icon(
//                                imageVector = if (navBarState == index) {
//                                    item.selectedIcon
//                                } else item.unselectedIcon,
//                                contentDescription = item.title,
//                            )
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
            composable(Screen.Plans.route) {
                NoteScreen(vm = noteViewModel, navController = navController)
            }
            composable(Screen.Progress.route) {
                SearchScreen(navController = navController)
            }
            composable(Screen.Settings.route) {
                ProfileScreen(navController = navController)
            }
        }
    }
}
