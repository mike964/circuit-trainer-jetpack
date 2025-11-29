package com.example.gmwrokouttimer

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
val sampleWorkoutSets = listOf(
    WorkoutSet(1, "Morning 10 mins", listOf(1,2,3)),
    WorkoutSet(2, "Upper Body Strength Circuit", listOf(2,3,4)),
    WorkoutSet(3, "Gym Day A : Chest & Biceps", listOf(4,5,6)),
    WorkoutSet(4, "Gym Day B : Back & Triceps", listOf(7,8,9)),
    WorkoutSet(5, "Outdoor Park Training", listOf(12,11,2,3,4)),
    WorkoutSet(6, "Burn Calories at Home", listOf(12,3,4,6, 1, 2))
)