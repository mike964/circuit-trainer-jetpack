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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.isPm
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
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
import com.example.gmwrokouttimer.utils.convertDateTimeToEpochMillis2
import com.example.gmwrokouttimer.utils.convertEpochMillisToLocalDate
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewActivityPopup(showPopup: Boolean = false, onDismiss: () -> Unit, onClickSave: () -> Unit) {

    val localDate = LocalDate.now()
    var title by remember { mutableStateOf("") }
    var dateTime by remember { mutableStateOf("$localDate") }
    var duration by remember { mutableStateOf("600") }  // 10 minutes
    var calories by remember { mutableStateOf("100") }
    var city by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("Iraq") }
    var note by remember { mutableStateOf("") }
    var workoutPresetId by remember { mutableStateOf("0") }
    var datePickerSelectedDateEpoch by remember { mutableLongStateOf(System.currentTimeMillis()) }
    var showTimePicker by remember { mutableStateOf(false) }
    var showDatePickerModal by remember { mutableStateOf(false) }

    val tps = rememberTimePickerState(
        initialHour = 17,
        initialMinute = 30,
        is24Hour = false // Set to true for 24-hour format
    )

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
                Column(
                    Modifier
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
//                    Text("Now : ${System.currentTimeMillis()}")  // output : 1776862680000
                    Text("Now : ${convertEpochMillisToLocalDate(System.currentTimeMillis())}")
//                    val selectedDateTimeEpochMillis = convertDateTimeToEpochMillis2("2025-04-21", "12:27")
                    val selectedDateTimeEpochMillis = convertDateTimeToEpochMillis2(dateTime, "${tps.hour}:${tps.minute}")
//                    Text(selectedDateTimeEpochMillis.toString())
                    Text(convertEpochMillisToLocalDate(selectedDateTimeEpochMillis))
//                    Text(convertEpochMillisToLocalDate( dateTime.toLong() ))  // Error
//                    Text(dateTime)  // 2026-04-07
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Title") },
                        placeholder = { Text("Cycling for 30 minutes") },
                        singleLine = true,
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Row() {
                        OutlinedTextField(
                            value = dateTime,
                            onValueChange = { newText -> dateTime = newText },
                            label = { Text("Date") },
                        )
                        IconButton(onClick = { showDatePickerModal = true }) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Add to favorites"
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    if (showDatePickerModal) {
                        DatePickerModal(onDateSelected = { selectedDate ->
                            if (selectedDate != null) {
//                                dateTime = selectedDate.toString()
                                datePickerSelectedDateEpoch = selectedDate
                                dateTime = convertEpochMillisToLocalDate(selectedDate, "yyyy-MM-dd")
                                showDatePickerModal = false
                            }
                        }) {  // onDismiss
                            showDatePickerModal = false
                        }
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                        TimePicker(state = timePickerState)
//                        Text("Selected Time: ${tps.hour}:${tps.minute} ${if (tps.isPm) "PM" else "AM"} ,${tps.is24hour}")
                        TimeInput(state = tps)
//                        Text(tps.toString())  // output : ref obj
                    }


                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(
                            Modifier
                                .width(120.dp)
                                .padding(4.dp, 2.dp)
                        ) {
                            OutlinedTextField(
                                value = duration,
//                                onValueChange = { duration = it.toInt() },
                                label = { Text("Duration (seconds)") },
                                onValueChange = { newValue ->
                                    // Only update state if the new string contains only digits
                                    if (  newValue.all { it.isDigit() }) {
                                        duration = newValue
                                        if ( newValue != "") calories = (newValue.toInt() / 60 * 13).toString()
                                    }
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                        }
                        Column(Modifier.padding(4.dp)) {
                            val formattedTime = if (duration != "") {
                                "%02d:%02d".format(  duration.toInt() / 60, duration.toInt() % 60)
                            } else {
                                "00:00"
                            }
                            Text(formattedTime)  // // Output: 01:05
                        }
                        Column(
                            Modifier
                                .width(100.dp)
                                .padding(8.dp, 2.dp)
                        ) {
                            OutlinedTextField(
                                value = calories,
                                onValueChange = { newValue ->
                                    // Only update state if the new string contains only digits
                                    if (newValue.all { it.isDigit() }) {
                                        calories = newValue
                                    }
                                },
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

                    /*
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

                     */

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

