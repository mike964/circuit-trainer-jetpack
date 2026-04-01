package com.example.gmwrokouttimer.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.decode.ImageDecoderDecoder

@Composable
fun DeterminateCircularIndicator(progress: Float) {
//    var progress by remember { mutableFloatStateOf(0.2f) } // Progress value from 0.0f to 1.0f

    // In a real app, you would update 'progress' based on your operation's status

    CircularProgressIndicator(
        progress = {
            progress // Use the progress parameter
        },
        modifier = Modifier.width(40.dp), // Adjust size
        color = MaterialTheme.colorScheme.secondary,
        strokeWidth = 4.dp,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
        strokeCap = StrokeCap.Round,
    )
}

@Composable
fun gifImageLoader(): ImageLoader {
    val context = LocalContext.current
    return remember(context) {
        ImageLoader.Builder(context)
            .components {
                add(ImageDecoderDecoder.Factory())
            }
            .build()
    }
}