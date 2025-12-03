package com.example.gmwrokouttimer.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.gmwrokouttimer.LocalImage


@Composable
fun ExerciseImageList(images:List<LocalImage> ) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(images) { image ->
            ImageCard(image = image)
        }
    }
}

@Composable
fun ImageCard(image: LocalImage) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
        Row {     Image(
            painter = painterResource(id = image.id),
            contentDescription = image.contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier
//                   .height(200.dp)
//                   .fillMaxWidth()
                .size(150.dp)
        )
            Text(
                text = image.contentDescription,
                modifier = Modifier.padding(16.dp)
            ) }
        }
    }
}