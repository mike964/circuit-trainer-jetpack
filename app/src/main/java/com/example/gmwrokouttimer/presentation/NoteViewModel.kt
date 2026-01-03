package com.example.gmwrokouttimer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.gmwrokouttimer.database.model.Note
import com.example.gmwrokouttimer.repository.NoteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {
    // # Expose data to UI reactively
    //    val todos: StateFlow<List<Todo>> = repository.allTodos
    //        .map { it.sortedByDescending { todo -> todo.id   }  }
    //        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val notes: StateFlow<List<Note>> = repository.allNotes
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addNote(title: String, content: String) {
        viewModelScope.launch {
            repository.insert(Note(title = title, content = content))
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.delete(note)
        }
    }
    // Update operation
    fun updateNote(note: Note) = viewModelScope.launch {
        repository.update(note)
    }
}
// # You don't have to use view model factory if you use hilt di
class NoteViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(repository) as T
    }
}