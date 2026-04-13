package com.example.gmwrokouttimer.repository

import com.example.gmwrokouttimer.database.ActivityDao
import com.example.gmwrokouttimer.database.model.Activity
import kotlinx.coroutines.flow.Flow

class ActivityRepository(private val activityDao: ActivityDao) {
    val allActivities: Flow<List<Activity>> = activityDao.getAllActivities()

    suspend fun insert(activity: Activity) {
        activityDao.upsertActivity(activity)
    }
    suspend fun update(activity: Activity) {
        activityDao.upsertActivity(activity)
    }

    suspend fun delete(activity: Activity) {
        activityDao.deleteActivity(activity)
    }
}