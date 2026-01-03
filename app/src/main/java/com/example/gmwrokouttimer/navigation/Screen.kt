package com.example.gmwrokouttimer.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object Main : Screen("main_screen")
    object Room : Screen("room_screen")

    object Search : Screen("search_screen")
    object Profile : Screen("profile_screen")
}
// 1. Define type-safe destinations