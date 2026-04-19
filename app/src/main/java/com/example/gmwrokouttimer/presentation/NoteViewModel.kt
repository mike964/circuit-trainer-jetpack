package com.example.gmwrokouttimer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.gmwrokouttimer.database.model.Activity
import com.example.gmwrokouttimer.database.model.Note
import com.example.gmwrokouttimer.repository.ActivityRepository
import com.example.gmwrokouttimer.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

//class NoteViewModel(private val repository: NoteRepository) : ViewModel() {
class NoteViewModel(private val repository: ActivityRepository) : ViewModel() {
    // # Expose data to UI reactively
    //    val todos: StateFlow<List<Todo>> = repository.allTodos
    //        .map { it.sortedByDescending { todo -> todo.id   }  }
    //        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Get activities in time range
    // Get this month all activities by default
//    val startDateTime = "2025-03-21T12:27:35.124365453"
    val startDateTime = "2026-04-13T12:27:35.124365453"
    val endDateTime = "2026-04-27T12:27:35.124365453"
//    val activitiesInTimePeriod: Flow<List<Activity>> =
//        repository.getActivitiesInPeriod( startDateTime , endDateTime) as StateFlow<List<Activity>>

    // Function to get filtered notes
    fun getNotesByDate(start: Long, end: Long) =
        repository.getActivitiesInPeriod(startDateTime, endDateTime)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    //    val notes: StateFlow<List<Note>> = repository.allNotes
    val activities: StateFlow<List<Activity>> = repository.allActivities
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

//    fun addNote(title: String, content: String) {
//        viewModelScope.launch {
//            repository.insert(Note(title = title, content = content))
//        }
//    }

    fun addActivity(activity: Activity) {
        viewModelScope.launch {
            repository.insert(activity)
        }
    }

//
//    fun deleteNote(note: Note) {
//        viewModelScope.launch {
//            repository.delete(note)
//        }
//    }
// Update operation
//    fun updateNote(note: Note) = viewModelScope.launch {
//        repository.update(note)
//    }
}
// # You don't have to use view model factory if you use hilt di
//class NoteViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return NoteViewModel(repository) as T
//    }
//}

class NoteViewModelFactory(private val repository: ActivityRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(repository) as T
    }
}