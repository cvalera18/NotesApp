package com.example.notesapp.data

import com.example.notesapp.model.Note
import com.example.notesapp.model.NoteProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

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

    fun saveNote(title: String, body: String, date: String) {
        val noteId = System.currentTimeMillis()
        println("Este es la date: $noteId")
        val newNote = Note(noteId, title, body, date)
        notesList.add(0, newNote)
    }
}
