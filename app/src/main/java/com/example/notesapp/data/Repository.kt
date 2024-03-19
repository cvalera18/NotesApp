package com.example.notesapp.data

import com.example.notesapp.model.Note
import com.example.notesapp.model.NoteProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Repository {
    private var notesList: MutableList<Note> = NoteProvider.modelNoteList.toMutableList()
    suspend fun getNotes(): List<Note> {
        return withContext(Dispatchers.IO) {
            return@withContext notesList
        }
    }
    fun searchNotes(userFilter: String): List<Note>{
        val filteredNotes = notesList
            .filter { note ->
                note.body.lowercase().contains(userFilter.lowercase())
            }
        return filteredNotes
    }
    fun onDeleteNote(newNote: Note) {
        val actual = notesList.firstOrNull { it.id == newNote.id }
        notesList.remove(actual)
    }

    fun saveNote(title: String, body: String) {
        val noteId = System.currentTimeMillis()
        println("Este es la date: $noteId")
        val newNote = Note(noteId, title, body, getCurrentDate())
        notesList.add(0, newNote)
    }
    fun updateNote(note: Note) {
        val noteIndex = notesList.indexOfFirst { it.id == note.id }
        if (noteIndex >= 0) {
            val updatedNote = note.copy(date = getCurrentDate())
            // Elimina la nota existente
            notesList.removeAt(noteIndex)
            // Agrega la nota actualizada al principio de la lista
            notesList.add(0, updatedNote)
        }
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd-MM-yy", Locale.getDefault())
        return dateFormat.format(Date())
    }
}
