package com.example.gmwrokouttimer

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {
    // # Stopwatch Timer
    private val _elapsedTime = MutableStateFlow(0L) // Time in milliseconds
    val elapsedTime: StateFlow<Long> = _elapsedTime.asStateFlow()

    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning.asStateFlow()

    private var lastTimeStamp = 0L

    fun startStopwatch() {
        if (_isRunning.value) {
            _isRunning.value = false
            return
        }

        _isRunning.value = true
        lastTimeStamp = System.currentTimeMillis() - _elapsedTime.value

        viewModelScope.launch {
            while (_isRunning.value) {
                delay(10L) // Update every 10ms for millisecond precision
                _elapsedTime.value = System.currentTimeMillis() - lastTimeStamp
            }
        }
    }

    fun resetStopwatch() {
        _isRunning.value = false
        _elapsedTime.value = 0L
        lastTimeStamp = 0L
    }

    // Helper function to format time (can be in a utility class)
    @SuppressLint("DefaultLocale")
    fun formatTime(milliseconds: Long): String {
        val totalSeconds = milliseconds / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        val millis = milliseconds % 1000
        return String.format("%02d:%02d.%03d", minutes, seconds, millis)
    }

    // # COUNTDOWN TIMER
    private val _timeLeft = MutableStateFlow(START_TIME_SECONDS)
    val timeLeft: StateFlow<Int> = _timeLeft.asStateFlow()

    companion object {
        private const val START_TIME_SECONDS = 60
        private const val INTERVAL_MILLIS = 1000L
    }

//    init {
//        startCountdown()
//    }

    fun startCountdown() {
        viewModelScope.launch {
            while (_timeLeft.value > 0) {
                delay(INTERVAL_MILLIS)
                _timeLeft.value -= 1
            }
        }
    }
    fun stopCountDown() {
        viewModelScope.launch {
            while (_timeLeft.value > 0) {
                delay(INTERVAL_MILLIS)
                _timeLeft.value -= 1
            }
        }
    }

    fun resetTimer() {
        _timeLeft.value = START_TIME_SECONDS
        // Re-launch the countdown if needed, depending on your app's logic
        // startCountdown()
    }


    // # SIMPLE COUNTER
// The state variable that holds the count value
    private var _count by mutableIntStateOf(0)
    val count: Int
        get() = _count

    // Function to increment the counter
    fun incrementCount() {
        _count++
    }

    // Function to decrement the counter (optional)
    fun decrementCount() {
        if (_count > 0) {
            _count--
        }
    }
}
