package com.example.notesapp.data.repository

import com.example.notesapp.model.Note
import com.example.notesapp.data.NoteProvider
import com.example.notesapp.utils.DateProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val noteProvider: NoteProvider,
    private val ioDispatcher: CoroutineDispatcher,
    private val dateProvider: DateProvider
) : Repository {
    private var notesList: MutableList<Note> = noteProvider.modelNoteList.toMutableList()
    override suspend fun getNotes(): List<Note> {
        return withContext(ioDispatcher) {
            return@withContext notesList
        }
    }
    override fun searchNotes(userFilter: String): List<Note>{
        val filteredNotes = notesList
            .filter { note ->
                note.body.lowercase().contains(userFilter.lowercase()) || note.title.lowercase().contains(userFilter.lowercase())
            }
        return filteredNotes
    }
    override fun onDeleteNote(newNote: Note) {
        val actual = notesList.firstOrNull { it.id == newNote.id }
        notesList.remove(actual)
    }

    override fun saveNote(title: String, body: String) {
        val noteId = System.currentTimeMillis()
        println("Este es la date: $noteId")
        val newNote = Note(noteId, title, body, dateProvider.getCurrentDate())
        notesList.add(0, newNote)
    }
    override fun updateNote(note: Note) {
        val noteIndex = notesList.indexOfFirst { it.id == note.id }
        if (noteIndex >= 0) {
            val updatedNote = note.copy(date = dateProvider.getCurrentDate())
            notesList.removeAt(noteIndex)
            notesList.add(0, updatedNote)
        }
    }
}
