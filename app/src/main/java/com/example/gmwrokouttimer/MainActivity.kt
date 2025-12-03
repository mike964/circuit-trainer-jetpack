package com.example.gmwrokouttimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gmwrokouttimer.components.CircularProgressBar
import com.example.gmwrokouttimer.components.CircularTimer
import com.example.gmwrokouttimer.components.ExerciseImageList
import com.example.gmwrokouttimer.components.ImageCard
import com.example.gmwrokouttimer.components.LocalGifExample
import com.example.gmwrokouttimer.ui.theme.GMWrokoutTimerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appViewModel = viewModel<AppViewModel>()
            val countdownVm = viewModel<CountdownViewModel>()
            val exerciseCounter by countdownVm.exerciseCounter.collectAsState()
            val currentPreset by appViewModel.currentPreset.collectAsState()
            val currentExercise =
                getExerciseById(currentPreset.exerciseIdList[exerciseCounter - 1])
            val timeRemaining by countdownVm.timeRemaining.collectAsState()
            val rounds by countdownVm.rounds.collectAsState()
            val circles by countdownVm.circles.collectAsState()
            val isRunning by countdownVm.isRunning.collectAsState()
            val isPaused by countdownVm.isPaused.collectAsState()
            val totalCircles = ((rounds * 2) - 1)

//            var currentImageId by remember { mutableIntStateOf(R.drawable.hamr_curl) }

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

//                        LocalGifExample(currentExercise.imageId)
//                        LocalGifExample(R.drawable.push_up)
                        LocalGifExample(currentExercise.imageId)


//                        Image(
////                            painter = painterResource(id = currentImageId),
//                            painter = painterResource(id = currentExercise.imageId),
//                            contentDescription = currentExercise.name,
//                            contentScale = ContentScale.Crop,
//                            modifier = Modifier
////                   .height(200.dp)
////                   .fillMaxWidth()
//                                .size(150.dp)
//                        )

                        Spacer(Modifier.height(16.dp))

                        // # Circular Remaining time n Rounds counters
                        if (isRunning || isPaused) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.size(100.dp)
                            ) {
                                CircularProgressBar(
                                    percentage = 1 - (circles.toFloat() / totalCircles.toFloat()),
                                    number = null
                                )
                                CircularTimer(1 - (timeRemaining.toFloat() / 10), timeRemaining)
                            }
                        }

                        CountdownScreen(countdownVm)

                        WorkoutsetList(appViewModel.workoutList, appViewModel, countdownVm)
//                        ExerciseImageList(appViewModel.exerciseImageList)
//                        ImageCard(LocalImage(id =  R.drawable.lat_raise , contentDescription = "Description for image one"))
                    }
                }
            }
        }
    }
}

