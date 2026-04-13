package com.example.gmwrokouttimer.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activities")
data class Activity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val rate: Int,
    val dateTime: String,
    val duration: Int,
    val calories: Int,
    val location: String?,
    val city: String,
    val country: String,
    val workoutPresetId: Int,
    val note: String,
    val imageId: Int?
)
