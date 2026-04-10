package com.example.gmwrokouttimer.presentation.plans

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup

@Composable
fun PresetPopup(showPopup: Boolean, onDismiss: () -> Unit) {
    Box() {


        if (showPopup) {
            // Popup appears anchored to its parent (the Box)
            Popup(
                alignment = Alignment.TopCenter,
                onDismissRequest = onDismiss
            ) {
                // Content of the popup
                Surface(
                    modifier = Modifier.size(200.dp, 50.dp),
                    color = Color.DarkGray,
                    shape = RoundedCornerShape(8.dp),
//                    elevation = 8.dp
                ) {
                    Text(
                        text = "I'm a Popup!",
                        color = Color.White,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}