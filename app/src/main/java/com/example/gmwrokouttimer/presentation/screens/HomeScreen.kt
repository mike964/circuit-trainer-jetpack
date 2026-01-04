package com.example.gmwrokouttimer.presentation.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gmwrokouttimer.navigation.Screen


@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Home Screen!")
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            // Navigate to the detail screen using its route
            navController.navigate(Screen.Progress.route)
        }) {
            Text("Go to Progress Screen")
        }
    }
}

@Composable
fun SearchScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Search Screen!")
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            // Go back to the previous screen (Home)
            navController.popBackStack()
        }) {
            Text("Go Back")
        }
    }
}
@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Profile Screen")
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            // Go back to the previous screen (Home)
            navController.popBackStack()
        }) {
            Text("Go Back")
        }
    }
}