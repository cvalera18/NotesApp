package com.example.notesapp.data.repository

import com.example.notesapp.model.Note
import com.example.notesapp.data.database.dao.NoteDao
import com.example.notesapp.model.toEntity
import com.example.notesapp.model.toNote
import com.example.notesapp.utils.DateProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val ioDispatcher: CoroutineDispatcher,
    private val dateProvider: DateProvider
) : Repository {

    override suspend fun getNotes(): List<Note> = withContext(ioDispatcher) {
        noteDao.getAllNotes().map { it.toNote() }
    }
    override suspend fun searchNotes(userFilter: String): List<Note> = withContext(ioDispatcher){
        val searchQuery = "%${userFilter.lowercase()}%"
        noteDao.searchNotes(searchQuery).map { it.toNote() }
    }
    override suspend fun onDeleteNote(newNote: Note) {
        withContext(ioDispatcher) {
            noteDao.delete(newNote.toEntity())
        }
    }
    override suspend fun saveNote(title: String, body: String) {
        withContext(ioDispatcher){
            val dateLong = System.currentTimeMillis()
            val noteEntity = Note(0, title, body, dateLong).toEntity()
            noteDao.insert(noteEntity)
        }
    }
    override suspend fun updateNote(note: Note) {
        withContext(ioDispatcher) {
            val noteEntity = note.toEntity()
            val updatedNoteEntity = noteEntity.copy(date = System.currentTimeMillis())
            noteDao.update(updatedNoteEntity)
        }
    }
}
