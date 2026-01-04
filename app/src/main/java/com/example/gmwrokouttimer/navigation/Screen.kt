package com.example.gmwrokouttimer.navigation
import com.example.gmwrokouttimer.R

sealed class Screen(val route: String) {
    object Main : Screen("main_screen")
    object Plans : Screen("plans_screen")
    object Progress : Screen("progress_screen")
    object Settings : Screen("settings_screen")
}
// 1. Define type-safe destinations
data class NavItemState(
    val title: String,
    val route: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
)

val navBarItems = listOf(
    NavItemState(
        "Main",
        "main_screen",
        R.drawable.ic_timer, R.drawable.ic_timer
    ),
    NavItemState(
        "Plans",
        "plans_screen",
        R.drawable.ic_format_list_bulleted, R.drawable.ic_format_list_bulleted
    ),
    NavItemState(
        "Progress",
        "progress_screen",
         R.drawable.ic_monitoring, R.drawable.ic_monitoring
    ),
    NavItemState(
        "Settings",
        "settings_screen",
        R.drawable.ic_settings, R.drawable.ic_settings
    ),
)