package com.example.gmwrokouttimer.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.gmwrokouttimer.database.model.Note
import kotlinx.coroutines.flow.Flow

// 2. Data Access Object (DAO)
@Dao
interface NoteDao {
    @Query("SELECT * FROM notes ORDER BY timestamp DESC")
    fun getAllNotes(): Flow<List<Note>> // Returns a reactive stream

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Upsert
    suspend fun upsertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}

