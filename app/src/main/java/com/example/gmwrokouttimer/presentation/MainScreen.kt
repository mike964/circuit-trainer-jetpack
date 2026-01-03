package com.example.gmwrokouttimer.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gmwrokouttimer.components.CircularProgressBar
import com.example.gmwrokouttimer.components.CircularTimer
import com.example.gmwrokouttimer.components.LocalGifExample

@Composable
fun MainScreen(viewModel: AppViewModel){
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
        val countdownVm = viewModel<CountdownViewModel>()
        val exerciseCounter by countdownVm.exerciseCounter.collectAsState()
        val currentPreset by viewModel.currentPreset.collectAsState()
        val currentExercise =
            getExerciseById(currentPreset.exerciseIdList[exerciseCounter - 1])
        val timeRemaining by countdownVm.timeRemaining.collectAsState()
        val rounds by countdownVm.rounds.collectAsState()
        val circles by countdownVm.circles.collectAsState()
        val isRunning by countdownVm.isRunning.collectAsState()
        val isPaused by countdownVm.isPaused.collectAsState()
        val totalCircles = ((rounds * 2) - 1)

        Text(
            text = currentPreset.name,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(4.dp)
        )
//                        Text(text = "Current Exercise ID : $currentExerciseId", fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp))
        Text(
            text = currentExercise.name,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(4.dp)
        )

        LocalGifExample(currentExercise.imageId)


        Spacer(Modifier.height(16.dp))

        // # Remaining time n Rounds circular counters
        if (isRunning || isPaused || exerciseCounter > 1 ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(120.dp)
            ) {
                CircularProgressBar(
                    percentage = 1 - (circles.toFloat() / totalCircles.toFloat()),
                    number = null,
                    radius = 56.dp
                )
                CircularTimer(1 - (timeRemaining.toFloat() / 10000), timeRemaining , 10)
            }
        }

        CountdownScreen(countdownVm)

        WorkoutsetList(viewModel.workoutList, viewModel, countdownVm)
//                        ExerciseImageList(appViewModel.exerciseImageList)
//                        ImageCard(LocalImage(id =  R.drawable.lat_raise , contentDescription = "Description for image one"))
    }
}