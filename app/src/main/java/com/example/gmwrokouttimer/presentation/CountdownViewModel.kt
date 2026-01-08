package com.example.gmwrokouttimer.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TimerState(
//    val todoList: List<TodoItem> = emptyList(),
    val isLoading: Boolean = false,
//    val error: String? = null
    val workTimeSeconds: Int = 5, // *1000 to get milliseconds
    val restTimeSeconds: Int = 10,
    val initRounds: Int = 1,
    val initExercises: Int = 1, // total exercise count comes from selected preset
)

class CountdownViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TimerState(isLoading = true))
    val uiState: StateFlow<TimerState> = _uiState.asStateFlow()

    //    private val initialTimeMilliseconds = 10000
    // total rounds count same as total exercises in a preset
    private val _roundsCounter = MutableStateFlow(uiState.value.initRounds)

    // Public immutable state for the UI to observe
    val roundsCounter: StateFlow<Int> = _roundsCounter.asStateFlow()

    private val _timeRemaining = MutableStateFlow(uiState.value.workTimeSeconds * 1000)


    // Get total number of work & rest circles
//    private var _circles = MutableStateFlow((_uiState.value.initExercises * 2) - 1)
    private var _circles = MutableStateFlow(1)
    val circles: StateFlow<Int> = _circles.asStateFlow()
    val exerciseCounter = MutableStateFlow(1)

    val timeRemaining: StateFlow<Int> = _timeRemaining.asStateFlow()


    private val _isRunning = MutableStateFlow(false)
    private val _isPaused = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning.asStateFlow()
    val isPaused: StateFlow<Boolean> = _isPaused.asStateFlow()

    private var timerJob: Job? = null

    fun setTotalExercises(x: Int) {

        _uiState.update { currentState ->
            currentState.copy(
                initExercises = x
            )
        }
        Log.d("xx", x.toString())
        _circles.value = (x * 2) - 1
    }

    fun startPauseTimer() {
        if (_isRunning.value) {
            pauseTimer()
        } else {
            startTimer()
        }
    }

    private fun startTimer() {
        Log.d("xx", uiState.value.toString())
        Log.d("xx", timeRemaining.value.toString())
        Log.d("xx", _circles.value.toString())
        if (_timeRemaining.value > 0 && timerJob?.isActive != true) {
            _isRunning.value = true
            _isPaused.value = false

            timerJob = viewModelScope.launch {
//                Log.d("xx", "Bitch..")

//                while (_roundsCounter.value > 0) {
                while (_circles.value > 0) {
//                    Log.d("xx", "while count..")
                    while (_timeRemaining.value > 0) {
//                        Log.d("xx", _timeRemaining.value.toString())
                        delay(100L) // Delay for one second
                        _timeRemaining.value -= 100
                    }
                    // Re-run timer
                    _timeRemaining.value = uiState.value.workTimeSeconds * 1000
                    _circles.value -= 1
                    if (!checkEvenNumber(_circles.value)
                        && exerciseCounter.value < uiState.value.initExercises
                    ) {
                        exerciseCounter.value++
                    }
//                    Log.d("xx", "Circuit end..")
                }
//                    _roundsCounter.value -= 1
//                }
                _isRunning.value = false
            }
        }
    }

    private fun pauseTimer() {
        timerJob?.cancel()
        _isPaused.value = true
        _isRunning.value = false
    }

    fun resetTimer() {
        timerJob?.cancel()
        _isRunning.value = false
        _isPaused.value = false
//        _timeRemaining.value = initialTimeMilliseconds
        _timeRemaining.value = uiState.value.workTimeSeconds * 1000
        exerciseCounter.value = 1
        _roundsCounter.value = uiState.value.initRounds
        _circles.value = (uiState.value.initExercises * 2) - 1
    }

    // Ensure the job is cancelled when the ViewModel is cleared
    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }

    /*
    fun toggleTodoCompletion(itemId: Int, isChecked: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                todoList = currentState.todoList.map { item ->
                    if (item.id == itemId) item.copy(isCompleted = isChecked) else item
                }
            )
        }
        // In a real app, you would also update the database here
    }
     */

    fun updateInitRounds(x: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                initRounds = x
            )
        }
//        _roundsCounter.value = x
    }
    fun updateWorkTime(x: Int) {
        val y = x* 5
        Log.d(y.toString(), "xx")
        _uiState.update { currentState ->
            currentState.copy(
                workTimeSeconds = y
            )
        }
    }


}
