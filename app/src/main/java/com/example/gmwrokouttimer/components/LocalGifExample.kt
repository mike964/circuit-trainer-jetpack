package com.example.gmwrokouttimer.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.gmwrokouttimer.R
import com.example.gmwrokouttimer.components.shared.GifImageLoader

@Composable
fun LocalGifExample(gifResourceId: Int?) {
    val context = LocalContext.current
    val imageLoader = GifImageLoader() // Custom ImageLoader from Step 2

    // Assuming you have a GIF named "my_animation.gif" in your res/drawable folder
//    val gifResourceId = R.drawable.st_shoulder_press

    Box(
        modifier = Modifier
            .height(200.dp)
            .width(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color.White)
        ,
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(gifResourceId)
                .build(),
            imageLoader = imageLoader,
            contentDescription = "Local Animated GIF",
        modifier = Modifier.fillMaxSize()

        )
    }

}