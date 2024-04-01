package com.example.notesapp.data.datasource.local

import com.example.notesapp.data.datasource.NoteDataSource
import com.example.notesapp.data.datasource.local.database.dao.NoteDao
import com.example.notesapp.domain.model.Note
import com.example.notesapp.domain.model.toEntity
import com.example.notesapp.domain.model.toNote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalNoteDataSource @Inject constructor(
    private val noteDao: NoteDao,
    private val ioDispatcher: CoroutineDispatcher
) : NoteDataSource {

    override suspend fun getNotes(): List<Note> = withContext(ioDispatcher) {
        noteDao.getAllNotes().map { it.toNote() }
    }

    override suspend fun searchNotes(userFilter: String): List<Note> = withContext(ioDispatcher) {
        val searchQuery = "%${userFilter.lowercase()}%"
        noteDao.searchNotes(searchQuery).map { it.toNote() }
    }

    override suspend fun deleteNote(note: Note) = withContext(ioDispatcher) {
        noteDao.delete(note.toEntity())
    }

    override suspend fun saveNote(note: Note) = withContext(ioDispatcher) {
        val noteEntity = note.toEntity()
        noteDao.insert(noteEntity)
    }

    override suspend fun updateNote(note: Note) = withContext(ioDispatcher) {
        val noteEntity = note.toEntity().copy(date = System.currentTimeMillis())
        noteDao.update(noteEntity)
    }
}