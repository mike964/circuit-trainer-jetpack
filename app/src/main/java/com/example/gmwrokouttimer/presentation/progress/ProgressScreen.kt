package com.example.gmwrokouttimer.presentation.progress

import kotlin.time.Duration.Companion.seconds
import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gmwrokouttimer.data.Activity
import com.example.gmwrokouttimer.presentation.AppViewModel
import com.example.gmwrokouttimer.utils.formatDateString

@SuppressLint("SuspiciousIndentation")
@Composable
fun ProgressScreen(appVm: AppViewModel, navController: NavController) {
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

        LazyColumn {
            items(activities, key = { it.id }) {
                ActivityListItem(it)
            }
        }
    }
}

@Composable
fun ActivityListItem(activity: Activity) {
    Column() {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .border(width = 1.dp, color = Color.Gray)
        ) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(8.dp)) {
//            Text(text = activity.id.toString())
                Text(text = activity.title)
                Text(text = formatDateString(activity.dateTime))
            }
            Column(modifier = Modifier
                .weight(1f)
                .padding(8.dp)) {
                Text(text = formatSeconds(activity.duration.toLong()))
                Text(text = "Rate : ${activity.rate}")
            }
        }
        Row() {
            Text("Note : ${activity.note}")
        }
    }
}

@SuppressLint("DefaultLocale")
fun formatSeconds(totalSeconds: Long): String {
    return totalSeconds.seconds.toComponents { hours, minutes, seconds, _ ->
        // Formats as HH:MM:SS
        String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}

/*
Instead of hardcoding colors like Color.Red, use your
 theme's color scheme (e.g., MaterialTheme.colorScheme.surface)
 to ensure support for both Light and Dark modes.
 */