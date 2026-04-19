package com.example.gmwrokouttimer.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.gmwrokouttimer.database.model.Activity
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityDao {
    @Query("SELECT * FROM activities ORDER BY dateTime DESC")
    fun getAllActivities(): Flow<List<Activity>> // Returns an observable stream

    @Query("SELECT * FROM activities WHERE dateTime BETWEEN :startTime AND :endTime ORDER BY dateTime DESC")
    fun getActivitiesInTimePeriod(startTime: String, endTime: String): Flow<List<Activity>>
    // Use Flow<List<Note>> for Kotlin Coroutines

    @Upsert // Handles both insert and update
    suspend fun upsertActivity(activity: Activity)

    @Delete
    suspend fun deleteActivity(activity: Activity)
}