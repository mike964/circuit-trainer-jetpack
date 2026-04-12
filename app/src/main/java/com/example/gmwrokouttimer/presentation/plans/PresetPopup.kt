package com.example.gmwrokouttimer.presentation.plans

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.gmwrokouttimer.data.Preset
import com.example.gmwrokouttimer.data.getExerciseById
import com.example.gmwrokouttimer.presentation.GifImageLocal

@Composable
fun PresetPopup(preset: Preset, showPopup: Boolean, onDismiss: () -> Unit) {

    val scrollState = rememberScrollState()

        if (showPopup) {
            // Popup appears anchored to its parent (the Box)
            Popup(
                alignment = Alignment.Center,
                onDismissRequest = onDismiss
            ) {
                // Content of the popup
                Surface(
                    modifier = Modifier.size(340.dp, 540.dp),
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp),
                    shadowElevation = 8.dp
                ) {
                    Column( Modifier.padding(16.dp).fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally) {

                        Text(preset.name  ,  fontWeight = FontWeight.Bold , fontSize = 20.sp,   textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth())
                        Text( "Exercises ${preset.exerciseIdList.size} ")
//                        Text( "id: ${preset.id} ")

                        Spacer(modifier = Modifier.height(8.dp))

                        Column(
//                        Modifier.background(Color.Cyan),
                            modifier = Modifier.verticalScroll(scrollState)
                        ) {
                            preset.exerciseIdList.forEachIndexed { index, exercise ->
                                Row(Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)){

                                    Column(Modifier.weight(2f)) {
                                        Text(  "${index + 1}. ${getExerciseById(exercise).name}",
                                            color = Color(0xFF29292C)  )
                                    }
                                    Column(Modifier.weight(1f)) {
                                        Box(
                                            modifier = Modifier
                                                .height(100.dp)
                                                .width(100.dp)
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(color = Color.LightGray)
                                            ,
                                            contentAlignment = Alignment.Center
                                        ) {
                                            GifImageLocal(getExerciseById(exercise).imageId)
                                        }
                                    }
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