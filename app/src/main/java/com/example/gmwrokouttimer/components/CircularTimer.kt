package com.example.gmwrokouttimer.components

import androidx.compose.animation.core.tween
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun CircularTimer(progress:Float , time:Int) {
    val totalTime = 60L // Total time in seconds
    var currentTime by remember { mutableStateOf(totalTime) }
    var isTimerRunning by remember { mutableStateOf(true) }

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
        targetValue = progress,
        animationSpec = tween(1000),
        label = "Timer Progress Animation"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(92.dp)
    ) {
        CircularProgressIndicator(
            progress = animatedProgress,
            modifier = Modifier
                .width(94.dp)
                .height(94.dp),
            strokeWidth = 6.dp,
            strokeCap = StrokeCap.Round, // Makes the ends rounded
        )
//        Text(text = "$currentTime s") // Display the time
        Text(text = "$time",  fontSize = 30.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary) // Display the time
    }
    // Add buttons here to control the timer (Start/Pause/Reset)
    // ...
}