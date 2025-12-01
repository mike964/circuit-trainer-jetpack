package com.example.gmwrokouttimer

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gmwrokouttimer.components.PlayButton


@Composable
fun WorkoutsetList(items: List<Preset>, appVm: AppViewModel, countdownVm: CountdownViewModel) {
    LazyColumn(modifier = Modifier.padding(4.dp)) {
        items(items, key = { item -> item.id }) { item ->
            WorkoutsetCard(item, { appVm.setCurrentPreset(item.id) }) {
//                // onPlayClick() Lambda
                countdownVm.startPauseTimer()
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WorkoutsetCard(item: Preset, onCardClick: () -> Unit, onPlayClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    // State to track if the row is selected
    var isSelected by remember { mutableStateOf(false) }

    // Define border properties based on the condition
    val borderColor = if (isSelected) Color(0xFF5E5E60) else Color.Gray
    val borderWidth = if (isSelected) 2.dp else 1.dp

    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 4.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White, // Set the background color here
            contentColor = Color.DarkGray // Optionally, set the color for content (text, icons) inside the card
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    border = BorderStroke(
                        width = borderWidth,
                        color =borderColor
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable {
                    Log.d("xx", "Clicked..")
                    isSelected = !isSelected
                    onCardClick()
                }
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
//                .background(Color.LightGray)
                    .weight(10f)
            ) {
                Text(text = item.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
//            Text(text = item.exercises[0].name)
                // Display Exercise names in a row

                FlowRow(
//                        Modifier.background(Color.Cyan),
                ) {
                    for (exercise in item.exerciseIdList) {
                        Text(
                            "${getExerciseNameById(exercise)}, ",
                            color = Color(0xFF787C85)
                        )
                    }
                    Text(item.exerciseIdList.size.toString())
                }
            }
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 12.dp)
                    .weight(2f)
//                    .background(Color.Cyan),
            ) {
                PlayButton(onPlayClick)
            }
        }
    }
}

