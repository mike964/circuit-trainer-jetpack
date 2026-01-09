package com.example.gmwrokouttimer.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.util.Locale

@Composable
fun Stopwatch() {
    var timeMillis by remember { mutableLongStateOf(0L) }
    var isRunning by remember { mutableStateOf(false) }

    // LaunchedEffect runs a coroutine when the screen is active.
    // It restarts if isRunning changes.
    LaunchedEffect(key1 = isRunning) {
        if (isRunning) {
            val startTime = System.currentTimeMillis() - timeMillis
            while (true) {
                timeMillis = System.currentTimeMillis() - startTime
                delay(10) // Update every 10 milliseconds
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display Time
        Text(
            text = formatTime(timeMillis),
            fontSize = 36.sp,
            style = MaterialTheme.typography.displayLarge
        )

        // Controls
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = { isRunning = !isRunning }) {
                Text(if (isRunning) "Pause" else "Start")
            }

            Button(onClick = {
                isRunning = false
                timeMillis = 0L
            }) {
                Text("Reset")
            }
        }
    }
}

// Helper function to format milliseconds to mm:ss:SS
fun formatTime(millis: Long): String {
    val totalSeconds = millis / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    val hundredths = (millis % 1000) / 10
    return String.format(Locale.getDefault(), "%02d:%02d:%02d", minutes, seconds, hundredths)
}