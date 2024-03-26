package com.example.notesapp.data.repository

import com.example.notesapp.model.Note
import com.example.notesapp.data.NoteProvider
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
    // TODO: Fix searchNotes function.
    override fun searchNotes(userFilter: String): List<Note>{
        val filteredNotes = emptyList<Note>()
            .filter { note ->
                note.body.lowercase().contains(userFilter.lowercase()) || note.title.lowercase().contains(userFilter.lowercase())
            }
        return filteredNotes
    }
    override suspend fun onDeleteNote(newNote: Note) {
        withContext(ioDispatcher) {
            noteDao.delete(newNote.toEntity())
        }
    }
    // TODO: Fix save order
    override suspend fun saveNote(title: String, body: String) {
        withContext(ioDispatcher){
            val noteEntity = Note(0, title, body, dateProvider.getCurrentDate()).toEntity()
            noteDao.insert(noteEntity)
        }
    }
    // TODO: Fix updateNote function, fix the order
    override suspend fun updateNote(note: Note) {
        withContext(ioDispatcher) {
            val updatedNoteEntity = note.toEntity()
            // Si tu DAO tiene un método de actualización, úsalo aquí.
            // Por ejemplo: noteDao.update(updatedNoteEntity)
            // Si no, puedes simplemente reinsertar el objeto para actualizarlo si tu @Insert maneja conflictos.
            noteDao.insert(updatedNoteEntity)
        }
    }
}
