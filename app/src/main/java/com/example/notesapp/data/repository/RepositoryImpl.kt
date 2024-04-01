package com.example.notesapp.data.repository

import com.example.notesapp.domain.model.Note
import com.example.notesapp.data.datasource.NoteDataSource
import com.example.notesapp.domain.repository.Repository
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
    override suspend fun onDeleteNote(note: Note) {
        noteDataSource.deleteNote(note)
    }
    override suspend fun saveNote(title: String, body: String) {
        val newNote = Note(id = 0, title = title, body = body, date = System.currentTimeMillis())
        noteDataSource.saveNote(newNote)
        }
    override suspend fun updateNote(note: Note) {
        noteDataSource.updateNote(note)
    }
}
