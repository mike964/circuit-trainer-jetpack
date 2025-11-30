package com.example.gmwrokouttimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gmwrokouttimer.components.LocalGifExample
import com.example.gmwrokouttimer.ui.theme.GMWrokoutTimerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val vm = viewModel<AppViewModel>()
            val countdownVm = viewModel<CountdownViewModel>()

            GMWrokoutTimerTheme {
                Surface(
                    color = Color(0xFFCCCED0),
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        // The modifier is applied to the Column itself
                        modifier = Modifier
                            .fillMaxSize() // Make the column occupy the whole screen
                            .padding(top = 50.dp),
                        // Arrange children evenly along the main (vertical) axis
                        verticalArrangement = Arrangement.SpaceAround,
                        // Center children horizontally within the column
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(text = "Push Ups", fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp))
//                        GifImage()
                        LocalGifExample()
//                        Counter(vm)
//                        Button({ countdownVm.startPauseTimer() }) {
//                            Text("+")
//                        }
//                        StopwatchScreen()
                        CountdownScreen(countdownVm)

//                        TimerOne(
//                            totalTime = 100L * 1000L,
//                            handleColor = Color.Magenta,
//                            inactiveBarColor = Color.DarkGray,
//                            activeBarColor = Color(0xFF37B900),
//                            modifier = Modifier.size(200.dp)
//                        )
//                        BasicCountdownTimer()
//                        MessageList(sampleMessages)
                        WorkoutSetList(sampleWorkoutSets, countdownVm)
                    }
                }
            }
        }
    }
}

