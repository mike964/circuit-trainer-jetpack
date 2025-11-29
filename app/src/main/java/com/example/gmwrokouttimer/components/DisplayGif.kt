package com.example.gmwrokouttimer.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun DisplayGif() {
    AsyncImage(
        model = "https://gifsec.com/wp-content/uploads/2022/10/miss-you-gif-17.gif",
        contentDescription = "Funny GIF",
        modifier = Modifier
            .size(200.dp)
            .clip(RectangleShape)
    )
}