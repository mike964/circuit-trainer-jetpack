package com.example.gmwrokouttimer.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Popup
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.gmwrokouttimer.components.HorizontalNumberPicker

@Composable
fun SettingsPopup(showPopup : Boolean = false, initRounds: Int, setInitRounds : (Int) -> Unit) {
    Popup(
        onDismissRequest = { showPopup   }, // Dismisses when tapping outside
        alignment = Alignment.Center // Centers the popup on the screen
    ) {
        // Content of the popup window
        Box(
            modifier = Modifier
                .width(400.dp)
                .height(540.dp)
                .dropShadow(
                    shape = RoundedCornerShape(16.dp),
                    shadow = Shadow(
                        radius = 9.dp,
                        spread = 3.dp,
                        color = Color(0x40000000),
                        offset = DpOffset(x = 1.dp, 1.dp)
                    )
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment =  Alignment.Center
        ) {
            Column {
                Text("This is a Popup Window!")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Work (Seconds)" )
                HorizontalNumberPicker(height = 30.dp) {

                }
                Text("Rest (Seconds)" )
                HorizontalNumberPicker(height = 30.dp) {

                }
                Text("Rounds" )
                HorizontalNumberPicker(default = initRounds, height = 30.dp) {
                   setInitRounds(it)
                }
//                Button(onClick = { showPopup = false }) {
//                    Text("Save")
//                }
            }
        }
    }
}

