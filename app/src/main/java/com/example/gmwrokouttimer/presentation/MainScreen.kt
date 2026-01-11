package com.example.gmwrokouttimer.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.gmwrokouttimer.components.CircularProgressBar
import com.example.gmwrokouttimer.components.CircularTimer
import com.example.gmwrokouttimer.components.CountdownTimerWithControls
import com.example.gmwrokouttimer.components.HorizontalNumberPicker
import com.example.gmwrokouttimer.components.LocalGifExample
import com.example.gmwrokouttimer.components.SimpleCountdownTimer
import com.example.gmwrokouttimer.components.Stopwatch
import com.example.gmwrokouttimer.utils.formatMilliseconds
import com.example.gmwrokouttimer.utils.formatSeconds

@Composable
fun MainScreen(viewModel: AppViewModel, navController: NavHostController) {
    val countdownVm = viewModel<CountdownViewModel>()
    val timerState by countdownVm.uiState.collectAsState()
    val exerciseCounter by countdownVm.exerciseCounter.collectAsState()
    val currentPreset by viewModel.currentPreset.collectAsState()
    val currentExercise =
        getExerciseById(currentPreset.exerciseIdList[exerciseCounter - 1])
    val timeRemaining by countdownVm.timeRemaining.collectAsState()
    val totalTime  by countdownVm.totalTimeSeconds.collectAsState()
    val totalTimeLeft by countdownVm.totalTimeLeft.collectAsState()
//    val rounds by countdownVm.rounds.collectAsState()
    val circles by countdownVm.circles.collectAsState()
    val isRunning by countdownVm.isRunning.collectAsState()
    val isPaused by countdownVm.isPaused.collectAsState()

    val totalCircles = ((timerState.initExercises * 2) - 1)
    val totalTimeSeconds =  ((timerState.workTimeSeconds) * timerState.initRounds * totalCircles)

    // State to control popup visibility
    var showPopup by remember { mutableStateOf(false) }

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (showPopup) {
            Popup(
                onDismissRequest = { showPopup = false }, // Dismisses when tapping outside
                alignment = Alignment.Center // Centers the popup on the screen
            ) {
                // Content of the popup window
                Box(
                    modifier = Modifier
                        .width(400.dp)
                        .height(540.dp)
                        .dropShadow(
                            shape = RoundedCornerShape(16.dp),
                            shadow = Shadow(
                                radius = 9.dp,
                                spread = 3.dp,
                                color = Color(0x40000000),
                                offset = DpOffset(x = 1.dp, 1.dp)
                            )
                        )
                        .align(Alignment.Center)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        Text("This is a Popup Window!")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Work (Seconds)")
                        HorizontalNumberPicker(
                            default = 1,
                            displayNumber = timerState.workTimeSeconds,
                            min = 2, max = 20,
                            height = 30.dp
                        ) {
                            countdownVm.setWorkTime(it)
                        }
                        Text("Rest (Seconds)")
                        HorizontalNumberPicker(height = 30.dp) {

                        }
                        Text("Rounds")
                        HorizontalNumberPicker(default = timerState.initRounds, height = 30.dp) {
                            countdownVm.setInitRounds(it)
                        }
                        Text(  "Total time : "
                                + formatSeconds((totalTimeSeconds).toLong()))

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(onClick = {
                            showPopup = false
                            Log.d("xx", "TimerState:")
                            Log.d("xx", timerState.toString())
                            Log.d("xx", "totalTime $totalTimeSeconds")
                            Log.d("xx", "totalTime $totalTime")
                        }) {
                            Text("Save")
                        }
                    }
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .background(Color(0xFFE8E8E8))
            .fillMaxSize(), // Make the column occupy the whole screen
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color(0xFF28336B)),
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Text("GM TRAINER 🥇", color = Color.White)
        }
        Spacer(Modifier.height(10.dp))


        // # Current Preset and Exercise)
        Row(verticalAlignment = CenterVertically) {
            Box(Modifier.weight(3f)) {}
            Box(
                modifier = Modifier.weight(6f),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = currentPreset.name,
                    fontWeight = FontWeight.Bold
                )
            }
            Box(Modifier.weight(3f), contentAlignment = Alignment.Center) {
                IconButton(
                    onClick = {
                        showPopup = true
//                        println("Settings button clicked!")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Settings, // Use a built-in Material icon
                        contentDescription = "Settings" // Provide content description for accessibility
                    )
                }
            }
        }
//                        Text(text = "Current Exercise ID : $currentExerciseId", fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp))
        Text(
            text = currentExercise.name,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )

        LocalGifExample(currentExercise.imageId)

        Spacer(Modifier.height(16.dp))

        // # Remaining time n Rounds circular counters
        if (isRunning || isPaused || exerciseCounter > 1) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(120.dp)
            ) {
                CircularProgressBar(
                    percentage = 1 - (circles.toFloat() / totalCircles.toFloat()),
                    number = null,
                    radius = 56.dp
                )
                CircularTimer(
                    1 - (timeRemaining.toFloat() / (timerState.workTimeSeconds * 1000)),
                    timeRemaining,
                    timerState.workTimeSeconds
                )
            }
            Text(timeRemaining.toString())
//            Text((timeRemaining.toFloat() / (timerState.workTimeSeconds*1000)).toString())
        }

        CountdownScreen(countdownVm)
//        Text(timerState.workTimeSeconds.toString())

//        SimpleCountdownTimer(120 )
//        Stopwatch()
        Text(formatMilliseconds(totalTimeLeft))

        WorkoutsetList(viewModel.workoutList, viewModel, countdownVm)
//                        ExerciseImageList(appViewModel.exerciseImageList)
//                        ImageCard(LocalImage(id =  R.drawable.lat_raise , contentDescription = "Description for image one"))
    }
}