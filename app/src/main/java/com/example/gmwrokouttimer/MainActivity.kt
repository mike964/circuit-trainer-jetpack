package com.example.gmwrokouttimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gmwrokouttimer.database.NoteDatabase
import com.example.gmwrokouttimer.presentation.AppViewModel
import com.example.gmwrokouttimer.presentation.NoteScreen
import com.example.gmwrokouttimer.presentation.NoteViewModel
import com.example.gmwrokouttimer.presentation.NoteViewModelFactory
import com.example.gmwrokouttimer.repository.NoteRepository
import com.example.gmwrokouttimer.ui.theme.GMWrokoutTimerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //
        val database = NoteDatabase.Companion.getDatabase(applicationContext)
        val repository = NoteRepository(database.noteDao())
        val factory = NoteViewModelFactory(repository)

         enableEdgeToEdge()
        setContent {
            val appViewModel = viewModel<AppViewModel>()
            val noteViewModel = viewModel<NoteViewModel>(factory = factory)

            GMWrokoutTimerTheme {
                Surface(
                    color = Color(0xFFCCCED0),
                    modifier = Modifier.Companion.fillMaxSize()
                ) {
//                    MainScreen(viewModel = appViewModel)
                    NoteScreen(noteViewModel)
                }
            }
        }
    }
}