package com.example.gmwrokouttimer

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CountdownViewModel : ViewModel() {

    private val initialTimeMilliseconds = 10000
    // total rounds count same as total exercises in a preset
    private val _roundsCount = MutableStateFlow(1)
    private val initExerciseCounter = 1
    private val _timeRemaining = MutableStateFlow(initialTimeMilliseconds)

    // Get total number of work & rest circles
    private var _circles = MutableStateFlow((_roundsCount.value * 2) - 1)
     val  exerciseCounter = MutableStateFlow(initExerciseCounter)


    val timeRemaining: StateFlow<Int> = _timeRemaining.asStateFlow()
    val circles: StateFlow<Int> = _circles.asStateFlow()
    // Public immutable state for the UI to observe
    val rounds: StateFlow<Int> = _roundsCount.asStateFlow()

    private val _isRunning = MutableStateFlow(false)
    private val _isPaused = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning.asStateFlow()
    val isPaused: StateFlow<Boolean> = _isPaused.asStateFlow()

    private var timerJob: Job? = null

    fun setRoundsCount(x:Int){
        _roundsCount.value = x
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
        if (_timeRemaining.value > 0 && timerJob?.isActive != true) {
            _isRunning.value = true
            _isPaused.value = false

            timerJob = viewModelScope.launch {
//                Log.d("xx", "Bitch..")

                while (_circles.value > 0) {
//                    Log.d("xx", "while count..")
                    while (_timeRemaining.value > 0) {
//                        Log.d("xx", "while time..")
                        delay(10L) // Delay for one second
                        _timeRemaining.value -= 10
                    }
                    // Timer finished
//                _isRunning.value = false
                    // Re-run timer
                    _timeRemaining.value = initialTimeMilliseconds
                    _circles.value -= 1
                    if (!checkEvenNumber(_circles.value)){
                         exerciseCounter.value++
                    }
//                    Log.d("xx", "Circuit end..")
                }
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
        _timeRemaining.value = initialTimeMilliseconds
        exerciseCounter.value = 1
        _circles.value = (_roundsCount.value * 2) - 1
    }

    // Ensure the job is cancelled when the ViewModel is cleared
    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}
