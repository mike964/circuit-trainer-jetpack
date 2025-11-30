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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gmwrokouttimer.components.LocalGifExample
import com.example.gmwrokouttimer.ui.theme.GMWrokoutTimerTheme
import kotlin.math.abs

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appViewModel = viewModel<AppViewModel>()
            val countdownVm = viewModel<CountdownViewModel>()
//            val isPaused by viewModel.isPaused.collectAsState()
            val exerciseCounter by countdownVm.exerciseCounter.collectAsState()
            val currentPreset = appViewModel.currentPreset
//            val currentExerciseId =   exerciseCounter-1
            val currentExercise = getExerciseNameById(currentPreset.exerciseIdList[ exerciseCounter-1])

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

//                        Text(text = "Preset Id : ${currentPreset.id}", fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp))
                        Text(text = "Preset : ${currentPreset.name}", fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp))
//                        Text(text = "Current Exercise ID : $currentExerciseId", fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp))
                        Text(text = "Current Exercise : $currentExercise", fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp))
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
                        WorkoutsetList(appViewModel.workoutList,appViewModel, countdownVm)
                    }
                }
            }
        }
    }
}

