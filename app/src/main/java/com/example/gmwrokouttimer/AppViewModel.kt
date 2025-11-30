package com.example.gmwrokouttimer

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {
    // Workout Plans list
    // The internal, mutable list (private)
    private val _workoutList = mutableStateListOf<Preset>()
    // # Current Selected Workout preset
     var currentPreset by mutableStateOf( Preset(1, "Morning 10 mins", listOf(1,2,3)))

    // The public, immutable list for the UI to observe
    val workoutList: List<Preset> get() = _workoutList

    init {
        // Initialize with some dummy data
        _workoutList.addAll( sampleWorkoutPresets)
    }
    // # SIMPLE COUNTER
// The state variable that holds the count value
    private var _count by mutableIntStateOf(0)
    private var _currentWorkoutsetId =  MutableStateFlow(0)
    private var _currentWorkoutsetExerciseCount by mutableIntStateOf(0)
    val currentWorkoutSetId : StateFlow<Int> = _currentWorkoutsetId.asStateFlow()
    val count: Int
        get() = _count

    // Function to increment the counter
    fun incrementCount() {
        _count++
    }
    fun setCurrentWorkoutsetId(id:Int){
        _currentWorkoutsetId.value = id
    }

    // Function to decrement the counter (optional)
    fun decrementCount() {
        if (_count > 0) {
            _count--
        }
    }
}
