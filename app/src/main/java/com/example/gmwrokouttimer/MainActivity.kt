package com.example.gmwrokouttimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gmwrokouttimer.database.AppDatabase
import com.example.gmwrokouttimer.presentation.AppViewModel
import com.example.gmwrokouttimer.presentation.NoteViewModel
import com.example.gmwrokouttimer.presentation.NoteViewModelFactory
import com.example.gmwrokouttimer.presentation.NavigationBar
import com.example.gmwrokouttimer.repository.ActivityRepository
import com.example.gmwrokouttimer.ui.theme.GMWrokoutTimerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //
        val database = AppDatabase.Companion.getDatabase(applicationContext)
//        val repository = NoteRepository(database.noteDao())
        val repository = ActivityRepository(database.activityDao(),)
        val noteViewModelFactory = NoteViewModelFactory(repository)

        enableEdgeToEdge()
        setContent {
            val appViewModel = viewModel<AppViewModel>()
            val noteViewModel = viewModel<NoteViewModel>(factory = noteViewModelFactory)

            GMWrokoutTimerTheme {
//                    MainScreen(viewModel = appViewModel)
//                    NoteScreen(noteViewModel)
                    NavigationBar(appViewModel, noteViewModel)
                }
        }
    }
}