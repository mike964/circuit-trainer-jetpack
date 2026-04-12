package com.example.gmwrokouttimer.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest


@SuppressLint("SuspiciousIndentation")
@Composable
fun GifImageLocal(gifResourceId: Int?) {
    val context = LocalContext.current
    val imageLoader = gifImageLoader() // Custom ImageLoader from Step 2

    // Assuming you have a GIF named "my_animation.gif" in your res/drawable folder
//    val gifResourceId = R.drawable.st_shoulder_press

        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(gifResourceId)
                .build(),
            imageLoader = imageLoader,
            contentDescription = "Local Animated GIF",
            modifier = Modifier.fillMaxSize().fillMaxHeight()
        )
    }
