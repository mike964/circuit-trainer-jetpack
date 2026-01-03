package com.example.gmwrokouttimer.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import kotlinx.coroutines.delay

@Composable
fun BasicCountdownTimer() {
    var timeLeft by remember { mutableIntStateOf(60) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = isRunning) {
        if (isRunning) {
            while (timeLeft > 0) {
                delay(1000L) // Delay for 1 second
                timeLeft--
            }
            isRunning = false // Stop when time runs out
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Time left: $timeLeft", style = MaterialTheme.typography.headlineLarge)
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { isRunning = !isRunning }) {
                Text(text = if (isRunning) "Pause" else "Start")
            }
            Button(onClick = {
                timeLeft = 60 // Reset time
                isRunning = false
            }) {
                Text(text = "Reset")
            }
        }
    }
}