package com.example.notesapp.data.repository

import com.example.notesapp.model.Note
import com.example.notesapp.data.datasource.NoteDataSource
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val noteDataSource: NoteDataSource
) : Repository {

    override suspend fun getNotes(): List<Note> {
        return noteDataSource.getNotes()
    }
    override suspend fun searchNotes(userFilter: String): List<Note> {
        return noteDataSource.searchNotes(userFilter)
    }
    override suspend fun onDeleteNote(newNote: Note) {
        noteDataSource.deleteNote(newNote)
    }
    override suspend fun saveNote(title: String, body: String) {
        val newNote = Note(id = 0, title = title, body = body, date = System.currentTimeMillis())
        noteDataSource.saveNote(newNote)
        }
    override suspend fun updateNote(note: Note) {
        noteDataSource.updateNote(note)
    }
}
