package com.example.notesapp.data.repository

import com.example.notesapp.model.Note

interface Repository {
    suspend fun getNotes(): List<Note>
    fun searchNotes(userFilter: String): List<Note>
    suspend fun onDeleteNote(newNote: Note)
    suspend fun saveNote(title: String, body: String)
    suspend fun updateNote(note: Note)
}