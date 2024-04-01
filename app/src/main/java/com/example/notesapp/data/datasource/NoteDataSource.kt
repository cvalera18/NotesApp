package com.example.notesapp.data.datasource

import com.example.notesapp.model.Note

interface NoteDataSource {
    suspend fun getNotes(): List<Note>
    suspend fun searchNotes(userFilter: String): List<Note>
    suspend fun deleteNote(note: Note)
    suspend fun saveNote(note: Note)
    suspend fun updateNote(note: Note)
}