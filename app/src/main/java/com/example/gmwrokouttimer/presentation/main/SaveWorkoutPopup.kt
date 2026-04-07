package com.example.gmwrokouttimer.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Popup
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp


@Composable
fun SaveWorkoutPopup(
    showPopup: Boolean = false,
    onDismiss: () -> Unit,
    note: String,
    onNoteChange: (String) -> Unit,
    handleBtnTwoClick: () -> Unit,
    onClickSave: () -> Unit,
) {
//    var showPopup by remember { mutableStateOf(false) }
    var userInput by remember { mutableStateOf("") }

//    Button(onClick = { showPopup = true }) {
//        Text("Open Popup")
//    }

//    val keyboardController = LocalSoftwareKeyboardController.current


    if (showPopup) {
        Popup(
            alignment = Alignment.Center,
//            onDismissRequest = { showPopup = false }
            onDismissRequest = onDismiss,
//            properties = PopupProperties(focusable = true) // CRITICAL for TextField input

        ) {
//            keyboardController?.show()
            // Custom layout for the popup window
            Box(
                modifier = Modifier
                    .width(380.dp)
                    .height(280.dp)
                    .dropShadow(
                        shape = RoundedCornerShape(16.dp),
                        shadow = Shadow(
                            radius = 8.dp,
                            spread = 2.dp,
                            color = Color(0x40000000),
                            offset = DpOffset(x = 1.dp, 1.dp)
                        )
                    )
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column() {
                    Text("Good job 👏")
                    Text("How was your workout?")
                    Text("How do you feel now?")
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
//                        value = userInput,
                        value = note,
//                        onValueChange = { userInput = it },
                        onValueChange = onNoteChange,
//                        label = { Text("Your Input") },
                        placeholder = { Text("Minimum 8 characters") },
                        singleLine = true,
                    )
                    Button(
                        onClick = {
//                            showPopup = false
                            onClickSave()
//                            onDismiss()
                        },
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Text("Save")
                    }
                    Button(
                        onClick = {
                            handleBtnTwoClick()
                        },
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Text("Btn 2")
                    }
                }
            }
        }
    }
}