package com.example.gmwrokouttimer.components
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gmwrokouttimer.R

@Composable
fun PlayButton() {
    // For a clickable icon (e.g., in a TopAppBar):
    IconButton(onClick = { /* action */ }) {
        Icon(
//            imageVector = Icons.Default.PlayArrow,
            painter = painterResource(id = R.drawable.play_circle_24dp),
            contentDescription = "Settings Icon",
            tint = Color(0xFF465B86),
                    modifier = Modifier.size(48.dp)
        )
    }
}