package com.example.gmwrokouttimer

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

data class Exercise(val id: Int, val name: String, val image: String)

//data class WorkoutSet(val id: Int, val name: String, val exercises: List<Exercise>)
data class Preset(val id: Int, val name: String, val exerciseIdList: List<Int>)
val sampleExercises = listOf(
    Exercise(1, "Push ups", "image"),
    Exercise(2, "Pull ups", "image"),
    Exercise(3, "Jump rope", "image"),
    Exercise(4, "Squats", "image"),
    Exercise(5, "Lat raise", "image"),
    Exercise(6, "Hammer press", "image"),
    Exercise(7, "Bicep Curl", "image"),
    Exercise(8, "Chest Fly", "image"),
    Exercise(9, "Triceps Ext", "image"),
    Exercise(10, "Shrugs", "image"),
    Exercise(11, "Dips", "image"),
    Exercise(12, "Stretch", "image"),
)
val sampleWorkoutPresets = listOf(
    Preset(1, "Morning 10 mins", listOf(1,2,3)),
    Preset(2, "Full Body Strength", listOf(2,3,4)),
    Preset(3, "Gym A - Chest & Biceps", listOf(4,5,6)),
    Preset(4, "Gym B - Back & Triceps", listOf(7,8,9)),
    Preset(5, "Outdoor Park Training", listOf(12,11,2,3,4)),
    Preset(6, "Burn Calories at Home", listOf(12,3,4,6, 1, 2))
)
fun getExerciseNameById(id: Int): String {
    val result = sampleExercises.filter { it.id == id }
    return result[0].name
}
fun getPresetById(id: Int): Preset {
    val result = sampleWorkoutPresets.filter { it.id == id }
    return result[0]
}

@Composable
fun StringListDisplay(items: List<String>) {
    Column { // or Row, Box, etc.
        for (name in items) {
            Text(name)
        }
    }
}
