package com.example.notesapp.domain.repository

import com.example.notesapp.domain.model.Note

interface Repository {
    suspend fun getNotes(): List<Note>
    suspend fun searchNotes(userFilter: String): List<Note>
    suspend fun onDeleteNote(note: Note)
    suspend fun saveNote(title: String, body: String)
    suspend fun updateNote(note: Note)
}