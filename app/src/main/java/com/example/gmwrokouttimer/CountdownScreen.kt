package com.example.gmwrokouttimer

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CountdownScreen(
//    viewModel: CountdownViewModel = viewModel()
    viewModel: CountdownViewModel
) {
    val timeRemaining by viewModel.timeRemaining.collectAsState()
    val circuitNumber by viewModel.circuitNumber.collectAsState()
    val isRunning by viewModel.isRunning.collectAsState()

    Column(
        modifier = Modifier
//            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Round: $circuitNumber")

        Text(
            text = "Time left: $timeRemaining s",
            fontSize =   30.sp
        )


        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
//                    .fillMaxWidth()
//                .background(Color.White)
                .background(Color(0xFF101F56),  shape = RoundedCornerShape(46.dp))
                .padding(horizontal = 16.dp , vertical = 12.dp)
        ) {
//            Button(onClick = { viewModel.startPauseTimer() }) {
//                Text(text = if (isRunning) "Pause" else "Start")
//            }
            IconButton(onClick = { viewModel.startPauseTimer() }) {
                Icon(
//                    imageVector = if (isRunning) Icons.Filled.Close else Icons.Filled.PlayArrow,
                    painter = painterResource(id = if (isRunning) R.drawable.pause_circle_24dp else R.drawable.play_circle),
                    contentDescription = "Play Pause Icon",
                    tint = Color(0xFFFFFFFF),
                    modifier = Modifier.size(40.dp)
                )
            }

            Button(onClick = { viewModel.resetTimer() }) {
                Text(text = "Reset")
            }
        }
    }
}