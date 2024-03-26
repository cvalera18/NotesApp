package com.example.notesapp.data.datasource

import com.example.notesapp.model.Note

interface NoteDataSource {
    suspend fun getNotes(): List<Note>
    suspend fun saveNote(note: Note)
    suspend fun deleteNote(noteId: Long)
}