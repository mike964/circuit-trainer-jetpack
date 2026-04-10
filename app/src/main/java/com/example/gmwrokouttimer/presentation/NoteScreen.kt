package com.example.gmwrokouttimer.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gmwrokouttimer.data.Exercise
import com.example.gmwrokouttimer.data.Preset
import com.example.gmwrokouttimer.data.getExerciseById
import com.example.gmwrokouttimer.database.model.Note
import com.example.gmwrokouttimer.presentation.plans.PresetPopup

@Composable
fun NoteScreen(appVm: AppViewModel, noteVm: NoteViewModel, navController: NavHostController) {
//    val notes by vm.notes.collectAsStateWithLifecycle()
//    var title by remember { mutableStateOf("") }
    val exercises = appVm.exercises
    var title by remember { mutableStateOf("") }
    var showPopup by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {

        Button(onClick = { showPopup = true }) {
            Text("Show Popup")
        }

        TextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
        Button(onClick = { noteVm.addNote(title, "Note content..."); title = "" }) {
            Text("Save Note")
        }

        PresetPopup(showPopup) {
            showPopup = false
            println("Button was clicked!")
        }

//        LazyColumn {
//            items(notes, key = { it.id }) { note ->
//                NoteItem(note, onDelete = { vm.deleteNote(note) })
//            }
//        }

        // # Display list of all exercises
//        LazyColumn {
//            items(exercises, key = { it.id }) { itm ->
//                ExerciseItem(itm )
//            }
//        }

        // # Display list of all workout presets
        LazyColumn {
//            items(exercises, key = { it.id }) { itm ->
//                ExerciseItem(itm )
//            }
            items(appVm.workoutList, key = { it.id }) { itm ->
                WorkoutPresetItem(itm)
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

@Composable
fun ExerciseItem(
    exercise: Exercise,
    //   , onDelete: (Exercise) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White, // Set background color here
//        contentColor = Color.Black       // Optional: Set default text/icon color
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = exercise.name)
            Text(text = exercise.imageId.toString())
        }
    }
}

@Composable
fun WorkoutPresetItem(
    preset: Preset,
    //       , onDelete: (WorkoutPreset) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White, // Set background color here
//        contentColor = Color.Black       // Optional: Set default text/icon color
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = preset.name + " - id: ${preset.id}")
            Text(text = preset.exerciseIdList.toString())
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

    }
}


/*
Instead of hardcoding colors like Color.Red, use your
 theme's color scheme (e.g., MaterialTheme.colorScheme.surface)
 to ensure support for both Light and Dark modes.
 */
