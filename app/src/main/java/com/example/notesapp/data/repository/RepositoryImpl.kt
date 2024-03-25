package com.example.notesapp.data.repository

import com.example.notesapp.model.Note
import com.example.notesapp.model.NoteProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val noteProvider: NoteProvider
) : Repository {
    private var notesList: MutableList<Note> = noteProvider.modelNoteList.toMutableList()
    override suspend fun getNotes(): List<Note> {
        return withContext(Dispatchers.IO) {
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
        val newNote = Note(noteId, title, body, getCurrentDate())
        notesList.add(0, newNote)
    }
    override fun updateNote(note: Note) {
        val noteIndex = notesList.indexOfFirst { it.id == note.id }
        if (noteIndex >= 0) {
            val updatedNote = note.copy(date = getCurrentDate())
            notesList.removeAt(noteIndex)
            notesList.add(0, updatedNote)
        }
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
        return dateFormat.format(Date())
    }
}
