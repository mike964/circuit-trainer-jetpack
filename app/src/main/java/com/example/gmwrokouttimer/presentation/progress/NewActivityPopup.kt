package com.example.gmwrokouttimer.presentation.progress

import android.R.attr.text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import java.time.LocalDate

@Composable
fun NewActivityPopup(showPopup: Boolean = false, onDismiss: () -> Unit, onClickSave: () -> Unit) {

    var title by remember { mutableStateOf("") }
    var dateTime by remember { mutableStateOf("${LocalDate.now()}") }
    var duration by remember { mutableIntStateOf(0) }
    var calories by remember { mutableIntStateOf(100) }
    var city by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("Iraq") }
    var note by remember { mutableStateOf("") }
    var workoutPresetId by remember { mutableStateOf("0") }


    if (showPopup) {
        Popup(
            alignment = Alignment.Center,
            onDismissRequest = onDismiss,
            properties = PopupProperties(focusable = true) // Crucial to capture focus for keyboard input
        ) {
            Box(
                modifier = Modifier
                    .width(380.dp)
                    .height(640.dp)
                    .dropShadow(
                        shape = RoundedCornerShape(12.dp),
                        shadow = Shadow(
                            radius = 8.dp,
                            spread = 3.dp,
                            color = Color(0x40000000),
                            offset = DpOffset(x = 1.dp, 1.dp)
                        )
                    )
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(Modifier.padding(16.dp).verticalScroll(rememberScrollState())) {
                    Text("Add new workout")
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Title") },
                        placeholder = { Text("Cycling for 30 minutes") },
                        singleLine = true,
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = dateTime,
                        onValueChange = { newText -> dateTime = newText },
                        label = { Text("Date & Time") },
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Row() {
                        Column(Modifier
                            .width(120.dp)
                            .padding(8.dp, 2.dp)) {
                            OutlinedTextField(
                                value = duration.toString(),
                                onValueChange = { duration = it.toInt() },
                                label = { Text("Duration (seconds)") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                        }
                        Column(Modifier.width(120.dp).padding(8.dp, 2.dp)) {
                            OutlinedTextField(
                                value = calories.toString(),
                                onValueChange = { calories = it.toInt() },
                                label = { Text("Calories") },
//                        placeholder = { Text("Minimum 8 characters") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = note,
                        onValueChange = { note = it },
                        label = { Text("Note") },
//                        placeholder = { Text("Minimum 8 characters") },
                        singleLine = true,
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = country,
                        onValueChange = { country = it },
                        label = { Text("Country") },
                        singleLine = true,
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = city,
                        onValueChange = { city = it },
                        label = { Text("City") },
//                        placeholder = { Text("Minimum 8 characters") },
                        singleLine = true,
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = workoutPresetId,
//                        onValueChange = { workoutPresetId = it.toInt() },
                        label = { Text("Preset Id") },
                        placeholder = { Text("Minimum 8 characters") },
                        onValueChange = { newValue ->
                            // Only update state if the new string contains only digits
                            if (newValue.all { it.isDigit() }) {
                                workoutPresetId = newValue
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    Button(
                        onClick = onClickSave,
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Text("Save")
                    }
                }
            }
        }
    }
}