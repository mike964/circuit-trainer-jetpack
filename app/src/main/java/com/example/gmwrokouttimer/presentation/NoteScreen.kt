package com.example.gmwrokouttimer.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.gmwrokouttimer.database.model.Note

@Composable
fun NoteScreen(vm: NoteViewModel,  navController: NavHostController) {
    val notes by vm.notes.collectAsStateWithLifecycle()
    var title by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
        Button(onClick = { vm.addNote(title, "Note content..."); title = "" }) {
            Text("Save Note")
        }

        LazyColumn {
            items(notes, key = { it.id }) { note ->
                NoteItem(note, onDelete = { vm.deleteNote(note) })
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