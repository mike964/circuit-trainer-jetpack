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
    Exercise(1, "Push up", R.drawable.push_up),
    Exercise(2, "Pull up", R.drawable.pullup),
    Exercise(3, "Jump Rope", R.drawable.jumprope),
    Exercise(4, "Simple Squat", R.drawable.squats),
    Exercise(5, "Squat Jump", R.drawable.squat_jump),
    Exercise(6, "Lat Raise", R.drawable.lateral_raise),
    Exercise(7, "Hammer Curl", R.drawable.hammer_curls),
    Exercise(8, "Bicep Curl", R.drawable.db_bicep_curls),
    Exercise(9, "Chest Fly", R.drawable.chest_fly),
    Exercise(10, "Triceps Ext", R.drawable.lying_tricep_extension),
    Exercise(11, "Triceps Kickback", R.drawable.tricepskickback),
    Exercise(12, "Shrugs", R.drawable.shrugs),
    Exercise(13, "Dips", R.drawable.band_dips),
    Exercise(14, "Crunch", R.drawable.crunch),
    Exercise(15, "Bicycle Crunch", R.drawable.bicycle_crunch_2),
    Exercise(16, "Band Pull apart", R.drawable.band_pull_aparts),
    Exercise(17, "Stretch", R.drawable.side_bends),
    Exercise(18, "Overhead Press", R.drawable.overhead_press),
    Exercise(19, "Bent Over Row", R.drawable.db_bent_row),
    Exercise(20, "Lunges", R.drawable.db_lunge),
    Exercise(21, "Arm Rotation", R.drawable.arm_circles),
    Exercise(22, "Jumping Jacks", R.drawable.jumping_jacks),
    Exercise(23, "High Knees", R.drawable.high_knees),
    Exercise(24, "High Jump", R.drawable.high_jump),
    Exercise(25, "Butt kickers", R.drawable.db_lunge),
    Exercise(26, "In Place Run", R.drawable.inplace_run),
    Exercise(27, "Butterfly Jump", R.drawable.butterfly),
    Exercise(28, "Shoulder Press", R.drawable.st_shoulder_press),
)
val sampleWorkoutPresets = listOf(
    Preset(1, "Morning 10 mins", listOf(1,2,3)),
    Preset(2, "Full Body Strength", listOf(4,5,6)),
    Preset(3, "Gym A - Chest & Biceps", listOf(21, 16 ,10 ,6)),
    Preset(4, "Gym B - Back & Triceps", listOf(5, 10 ,24, 21)),
    Preset(5, "Outdoor Park Training", listOf(3,4,2,13,24)),
    Preset(6, "Burn Calories at Home", listOf(21,22,27,24,25, 26))
)

val exerciseImages = listOf(
    LocalImage(id = R.drawable.push_up, contentDescription = "Description for image one"),
    LocalImage(id = R.drawable.pullup, contentDescription = "Description for image two"),
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
