package com.example.gmwrokouttimer.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.unit.sp
import com.example.gmwrokouttimer.utils.formatSeconds
import kotlinx.coroutines.delay

@Composable
fun SimpleCountdownTimer(initialTime: Int) {
    var timeLeft by remember { mutableIntStateOf(initialTime) }

    // LaunchedEffect runs when the composable enters the composition
    // and re-runs if 'timeLeft' changes.
    LaunchedEffect(key1 = timeLeft) {
        if (timeLeft > 0) {
            delay(1000L) // Wait for 1 second
            timeLeft -= 1
        }
    }

    Text(text = "Time left: $timeLeft", fontSize = 24.sp)
}

@Composable
fun CountdownTimerWithControls(totalSeconds: Int, isRunning : Boolean = false) {
    var timeLeft by remember { mutableIntStateOf(totalSeconds) }
//    var isRunning by remember { mutableStateOf(false) }
    var isRunning by remember { mutableStateOf(isRunning) }

    // Core Timer Logic
    LaunchedEffect(key1 = timeLeft, key2 = isRunning) {
        if (isRunning && timeLeft > 0) {
            delay(1000L)
            timeLeft -= 1
        } else if (timeLeft == 0) {
            isRunning = false
        }
    }

    Column {
        Text(text = "Time: ${formatSeconds(timeLeft.toLong())}s", fontSize = 32.sp)

       Row{
           Button(onClick = { isRunning = !isRunning }) {
               Text(if (isRunning) "Pause" else "Start")
           }

           Button(onClick = {
               isRunning = false
               timeLeft = totalSeconds
           }) {
               Text("Reset")
           }
       }
    }
}