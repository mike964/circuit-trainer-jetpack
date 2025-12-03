package com.example.gmwrokouttimer

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import com.example.gmwrokouttimer.R
import androidx.compose.ui.unit.dp

data class Exercise(val id: Int, val name: String, val imageId: Int)
//data class WorkoutSet(val id: Int, val name: String, val exercises: List<Exercise>)
data class Preset(val id: Int, val name: String, val exerciseIdList: List<Int>)
data class LocalImage(val id: Int, val contentDescription: String)
val sampleExercises = listOf(
    Exercise(1, "Push ups", R.drawable.lat_raise),
    Exercise(2, "Pull ups", R.drawable.arm_circles),
    Exercise(3, "Jump rope", R.drawable.bicycle_ab),
    Exercise(4, "Squats", R.drawable.st_shoulder_press),
    Exercise(5, "Lat raise", R.drawable.bicycle_ab),
    Exercise(6, "Hammer press", R.drawable.burpees),
    Exercise(7, "Bicep Curl", R.drawable.db_lunge),
    Exercise(8, "Chest Fly", R.drawable.high_knees),
    Exercise(9, "Triceps Ext", R.drawable.high_knees),
    Exercise(10, "Shrugs", R.drawable.jump_squat),
    Exercise(11, "Dips", R.drawable.jump_squat),
    Exercise(12, "Stretch", R.drawable.side_bends),
)
val sampleWorkoutPresets = listOf(
    Preset(1, "Morning 10 mins", listOf(1,2,3)),
    Preset(2, "Full Body Strength", listOf(2,3,4)),
    Preset(3, "Gym A - Chest & Biceps", listOf(4,5,6)),
    Preset(4, "Gym B - Back & Triceps", listOf(7,8,9)),
    Preset(5, "Outdoor Park Training", listOf(12,11,2,3,4)),
    Preset(6, "Burn Calories at Home", listOf(12,3,4,6, 1, 2))
)

val exerciseImages = listOf(
    LocalImage(id = R.drawable.lat_raise, contentDescription = "Description for image one"),
    LocalImage(id = R.drawable.inc_pushup, contentDescription = "Description for image two"),
    LocalImage(id = R.drawable.side_bends, contentDescription = "Description for image three"),
    // Add more images as needed
)
fun getExerciseById(id: Int): Exercise {
    val result = sampleExercises.filter { it.id == id }
    return result[0]
}
fun getPresetById(id: Int): Preset {
    val result = sampleWorkoutPresets.filter { it.id == id }
    return result[0]
}

//@Composable
//fun StringListDisplay(items: List<String>) {
//    Column { // or Row, Box, etc.
//        for (name in items) {
//            Text(name)
//        }
//    }
//}
