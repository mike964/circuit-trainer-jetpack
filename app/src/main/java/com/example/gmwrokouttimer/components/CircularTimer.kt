package com.example.gmwrokouttimer.components

import androidx.compose.animation.core.tween
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CircularTimer(
    progress: Float,
    time: Int,
    totalTime: Int,
    finished: Boolean = false,
    color: Color = Color(0xFF442585)
) {
//    val totalTime = 60L // Total time in seconds
//    var currentTime by remember { mutableStateOf(totalTime) }
//    var isTimerRunning by remember { mutableStateOf(true) }

    // LaunchedEffect to manage the countdown logic
//    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
//        if (currentTime > 0 && isTimerRunning) {
//            delay(1000L)
//            currentTime--
//        }
//    }

    // Calculate progress (value from 0.0 to 1.0)
//    val progress = currentTime.toFloat() / totalTime.toFloat()
    // Optional: Add smooth animation to the progress bar movement
    val animatedProgress by animateFloatAsState(
        targetValue = if(time / 1000 == totalTime) 1f else  progress,
        animationSpec = tween(100),
        label = "Timer Progress Animation"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(92.dp)
    ) {
        CircularProgressIndicator(
            trackColor = Color(0xFFD7D7DE),
            color = color,
            progress = { animatedProgress },
            modifier = Modifier
                .size(100.dp),
            strokeWidth = 6.dp,
            strokeCap = StrokeCap.Round, // Makes the ends rounded
        )
//        Text(text = "$currentTime s") // Display the time
//        if (time / 1000 == totalTime) {
//            Text(
//                "DONE",
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Bold,
//                color = MaterialTheme.colorScheme.primary
//            )
//        } else {
            Text(
                text = if (finished) "DONE" else "${((time + 1000) / 1000)}",
                fontSize = if (finished)  24.sp else 30.sp ,
                fontWeight = FontWeight.Bold,
                color = if (finished)  Color(0xFF1F3C94) else color
            )
//        }
    }
    // Add buttons here to control the timer (Start/Pause/Reset)
    // ...
}