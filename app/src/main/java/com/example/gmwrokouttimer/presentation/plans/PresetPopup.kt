package com.example.gmwrokouttimer.presentation.plans

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.example.gmwrokouttimer.data.Preset
import com.example.gmwrokouttimer.data.getExerciseById

@Composable
fun PresetPopup(preset: Preset, showPopup: Boolean, onDismiss: () -> Unit) {

        if (showPopup) {
            // Popup appears anchored to its parent (the Box)
            Popup(
                alignment = Alignment.Center,
                onDismissRequest = onDismiss
            ) {
                // Content of the popup
                Surface(
                    modifier = Modifier.size(300.dp, 400.dp),
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp),
                    shadowElevation = 8.dp
                ) {
                    Column( Modifier.padding(16.dp)) {

                        Text(preset.name + " - id: ${preset.id}" ,  fontWeight = FontWeight.Bold)
                        Text( "Exercises ${preset.exerciseIdList.size} ")

                        Spacer(modifier = Modifier.height(8.dp))

                        Column(
//                        Modifier.background(Color.Cyan),
                        ) {
                            preset.exerciseIdList.forEachIndexed { index, exercise ->
                                Row(Modifier.fillMaxWidth().padding(8.dp)){
                                    Text(  "${index + 1}. ${getExerciseById(exercise).name}, ",
                                        color = Color(0xFF29292C)  )
                                }
                                HorizontalDivider(
                                    thickness = 1.dp,
                                    color = Color.LightGray
                                )
                            }
                        }
                    }
                }
            }
        }
}