package com.example.gmwrokouttimer.components

import androidx.compose.animation.core.tween
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// # Great Example
@Composable
fun AnimatedCountdownTimer(viewModel: CountdownViewModel = viewModel()) {
    val currentTime by viewModel.currentTime.collectAsState()
    val isRunning by viewModel.isRunning.collectAsState()
    val progress = 1- viewModel.getProgress()

    // Smoothly animate the progress value
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 100), // Smooth animation between value changes
        label = "countdown_progress_animation"
    )

    Column(
//        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                progress = { animatedProgress },
                modifier = Modifier.size(150.dp),
                color = Color.Blue,
                trackColor = Color(0xFFD7D7DE),
                strokeWidth = 12.dp,
            )

            Text(
                text = (currentTime / 1000L).toString(), // Display time in seconds
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Button(onClick = { viewModel.startPauseTimer() }) {
                Text(text = if (isRunning) "Pause" else "Start")
            }
            Button(onClick = { viewModel.resetTimer() }, enabled = !isRunning) {
                Text(text = "Reset")
            }
        }
    }
}

class CountdownViewModel : ViewModel() {
    private val totalTime = 10000L // 10 seconds in milliseconds
    private val _currentTime = MutableStateFlow(totalTime)
    val currentTime: StateFlow<Long> = _currentTime.asStateFlow()

    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning.asStateFlow()

    fun startPauseTimer() {
        _isRunning.value = !_isRunning.value
        if (_isRunning.value) {
            viewModelScope.launch {
                while (_currentTime.value > 0 && _isRunning.value) {
                    delay(10L) // Update frequently for smooth animation
                    _currentTime.value -= 10L
                }
                if (_currentTime.value <= 0) {
                    _isRunning.value = false
                }
            }
        }
    }

    fun resetTimer() {
        _isRunning.value = false
        _currentTime.value = totalTime
    }

    fun getProgress(): Float {
        return _currentTime.value / totalTime.toFloat()
    }
}