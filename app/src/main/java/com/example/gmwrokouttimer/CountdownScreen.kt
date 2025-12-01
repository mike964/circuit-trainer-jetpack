package com.example.gmwrokouttimer

import android.view.SoundEffectConstants
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CountdownScreen(
//    viewModel: CountdownViewModel = viewModel()
    viewModel: CountdownViewModel,
) {
    val view = LocalView.current
    val timeRemaining by viewModel.timeRemaining.collectAsState()
    val circle by viewModel.circles.collectAsState()
    val exerciseCounter by viewModel.exerciseCounter.collectAsState()
    val isRunning by viewModel.isRunning.collectAsState()
    val isPaused by viewModel.isPaused.collectAsState()

    fun timerBgColor(): Long {
//        val Purple40 = Color(0xFFD58812)
        if (isRunning || isPaused){
            return if (checkNumber(circle))  0xFFD58812
            else 0xFF4ABE1A
        }
        return  0xFF101F56
    }

    Column(
        modifier = Modifier
//            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Round $exerciseCounter - $circle")

        Text(
            text = "Time left: $timeRemaining s",
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .background(Color(timerBgColor()), shape = RoundedCornerShape(46.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
//            Button(onClick = { viewModel.startPauseTimer() }) {
//                Text(text = if (isRunning) "Pause" else "Start")
//            }
            IconButton(onClick = {
                view.playSoundEffect(SoundEffectConstants.CLICK)
                viewModel.startPauseTimer()
            }) {
                Icon(
//                    imageVector = if (isRunning) Icons.Filled.Close else Icons.Filled.PlayArrow,
                    painter = painterResource(id = if (isRunning) R.drawable.pause_circle_24dp else R.drawable.play_circle),
                    contentDescription = "Play Pause Icon",
                    tint = Color(0xFFFFFFFF),
                    modifier = Modifier.size(40.dp)
                )
            }

            Button(onClick = {
                viewModel.resetTimer()
            }) {
                Text(text = "Reset")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        // # Show Work/Rest title when click start
        if (isRunning) {
            Text(
                text = (if (checkNumber(circle)) "REST" else "WORK")
            )
        } else if (isPaused) {
            Text(
                text = ("PAUSED")
            )
        }
    }




}

fun checkNumber(number: Int): Boolean {
    // If number is even return true, else return false
    return number % 2 == 0
}

