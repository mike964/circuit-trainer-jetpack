package com.example.gmwrokouttimer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CountdownViewModel : ViewModel() {

    private val initialTimeSeconds = 10
    private val initialCircuitsCount = 5
    private val _timeRemaining = MutableStateFlow(initialTimeSeconds)
    private val _circuitsCount = MutableStateFlow(initialCircuitsCount)

    val timeRemaining: StateFlow<Int> = _timeRemaining.asStateFlow()
    val circuitNumber: StateFlow<Int> = _circuitsCount.asStateFlow()

    private val _isRunning = MutableStateFlow(false)
    private val _isPaused = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning.asStateFlow()
    val isPaused: StateFlow<Boolean> = _isPaused.asStateFlow()

    private var timerJob: Job? = null

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
                while (_circuitsCount.value > 0) {
//                    Log.d("xx", "while count..")
                    while (_timeRemaining.value > 0) {
//                        Log.d("xx", "while time..")
                        delay(1000L) // Delay for one second
                        _timeRemaining.value -= 1
                    }
                    // Timer finished
//                _isRunning.value = false
                    // Re-run timer
                    _timeRemaining.value = initialTimeSeconds
                    _circuitsCount.value -= 1
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
        _timeRemaining.value = initialTimeSeconds
         _circuitsCount.value = initialCircuitsCount
    }

    // Ensure the job is cancelled when the ViewModel is cleared
    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}
