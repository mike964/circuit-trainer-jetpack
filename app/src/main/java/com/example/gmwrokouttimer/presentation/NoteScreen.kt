package com.example.gmwrokouttimer.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gmwrokouttimer.data.Preset
import com.example.gmwrokouttimer.data.getExerciseById
import com.example.gmwrokouttimer.database.model.Note
import com.example.gmwrokouttimer.presentation.plans.PresetPopup

@Composable
fun NoteScreen(appVm: AppViewModel, noteVm: NoteViewModel, navController: NavHostController) {
//    val notes by vm.notes.collectAsStateWithLifecycle()
//    var title by remember { mutableStateOf("") }
//    val exercises = appVm.exercises
//    var title by remember { mutableStateOf("") }
    var showPopup by remember { mutableStateOf(false) }
    var selectedPreset by remember { mutableStateOf(appVm.workoutList.getOrNull(0)) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                Modifier.weight(1f)
            ) {
                Text(
                    text = "< Back",
                    color = Color.Blue,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .clickable {
                            // Go back to the previous screen (Home)
                            navController.popBackStack()
                        }
                )
            }
            Column(Modifier.weight(3f), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Workout Plans", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
            }
            Column(Modifier.weight(1f)) { }
        }
        HorizontalDivider(thickness = 0.5.dp, color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))

        // Test workout details popup window
//        Button(onClick = { showPopup = true }) {
//            Text("Show Popup")
//        }

        /*
        // Room DB Test
        TextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
        Button(onClick = {
          noteVm.addNote(title, "Note content..."); title = ""
        }) {
            Text("Save Note")
        }
         */

        selectedPreset?.let {
            PresetPopup(it, showPopup) {
                showPopup = false
                println("Button was clicked!")
            }
        }

//        LazyColumn {
//            items(notes, key = { it.id }) { note ->
//                NoteItem(note, onDelete = { vm.deleteNote(note) })
//            }
//        }

        // # Display list of all workout presets
        LazyColumn {
            items(appVm.workoutList, key = { it.id }) { itm ->
                WorkoutPresetItem(itm) {
//                    appVm.setCurrentPreset(itm.id)
//                    navController.navigate("main")
                    selectedPreset = itm
                    showPopup = true
                }
            }
        }

    }
}

@Composable
fun NoteItem(note: Note, onDelete: (Note) -> Unit) {
    // Design your individual note item UI here (e.g., Card, Text fields, delete button)
    Text(text = note.title)
    // ...
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WorkoutPresetItem(
    preset: Preset,
    //       , onDelete: (WorkoutPreset) -> Unit
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White, // Set background color here
//        contentColor = Color.Black       // Optional: Set default text/icon color
        ),
        onClick = onClick
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Column(Modifier.weight(3f)) {
                Text(text = "  ${preset.name}")
//                Text(text = preset.exerciseIdList.toString())
                FlowRow(
//                        Modifier.background(Color.Cyan),
                ) {
                    for (exercise in preset.exerciseIdList) {
                        Text(
                            "${getExerciseById(exercise).name}, ",
                            color = Color(0xFF787C85)
                        )
                    }
                    Text(preset.exerciseIdList.size.toString())
                }
            }
            Column(Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .height(90.dp)
//                                                .width(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = Color.LightGray)
                ) {
                    if (preset.exerciseIdList.isNotEmpty()) {
                        GifImageLocal(getExerciseById(preset.exerciseIdList[0]).imageId)
                    }
                }
            }

        }

    }
}


/*
Instead of hardcoding colors like Color.Red, use your
 theme's color scheme (e.g., MaterialTheme.colorScheme.surface)
 to ensure support for both Light and Dark modes.
 */
