package com.example.notesapp.data.repository

import com.example.notesapp.model.Note

interface Repository {
    suspend fun getNotes(): List<Note>
    fun searchNotes(userFilter: String): List<Note>
    fun onDeleteNote(newNote: Note)
    fun saveNote(title: String, body: String)
    fun updateNote(note: Note)
}