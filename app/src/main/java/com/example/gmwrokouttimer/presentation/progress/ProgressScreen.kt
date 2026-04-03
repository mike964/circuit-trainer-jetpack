package com.example.gmwrokouttimer.presentation.progress

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gmwrokouttimer.data.Activity
import com.example.gmwrokouttimer.presentation.AppViewModel

@Composable
fun ProgressScreen(appVm: AppViewModel,navController: NavController) {
  val activities = appVm.latestActivity

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Progress Screen")
        Text("Latest activity")
        Text("Active days table")
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            // Go back to the previous screen (Home)
            navController.popBackStack()
        }) {
            Text("Go Back")
        }

        LazyColumn  {
            items(activities, key = { it.id }) {
                ActivityListItem(it)
            }
        }
    }
}

@Composable
fun ActivityListItem (activity: Activity) {
    Text("${activity.id} - Activity: ${activity.title}")
}