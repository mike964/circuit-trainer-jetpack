package com.example.gmwrokouttimer.repository

import com.example.gmwrokouttimer.database.ActivityDao
import com.example.gmwrokouttimer.database.model.Activity
import kotlinx.coroutines.flow.Flow

class ActivityRepository(private val activityDao: ActivityDao) {
    val allActivities: Flow<List<Activity>> = activityDao.getAllActivities()

//    val activitiesInTimePeriod: Flow<List<Activity>> = activityDao.getActivitiesInTimePeriod()

//    fun getNotesInPeriod(start: Long, end: Long): Flow<List<Activity>> {
    fun getActivitiesInPeriod(start: String, end: String): Flow<List<Activity>> {
        return activityDao.getActivitiesInTimePeriod(start, end)
    }

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