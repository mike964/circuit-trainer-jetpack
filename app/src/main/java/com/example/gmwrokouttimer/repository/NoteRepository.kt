package com.example.gmwrokouttimer.repository

import com.example.gmwrokouttimer.database.NoteDao
import com.example.gmwrokouttimer.database.model.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {
    val allNotes: Flow<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note) {
        noteDao.upsertNote(note)
    }
    suspend fun update(note: Note) {
        noteDao.upsertNote(note)
    }

    suspend fun delete(note: Note) {
        noteDao.deleteNote(note)
    }


}