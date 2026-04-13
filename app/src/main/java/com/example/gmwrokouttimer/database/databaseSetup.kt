package com.example.gmwrokouttimer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gmwrokouttimer.database.model.Note
import com.example.gmwrokouttimer.database.model.Activity

// 3. Room Database
@Database(entities = [Note::class, Activity::class], version = 1)
abstract class AppDatabase  : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun activityDao(): ActivityDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase ? = null

        fun getDatabase(context: Context): AppDatabase  {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase ::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}