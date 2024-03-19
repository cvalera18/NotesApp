package com.example.notesapp.data

import com.example.notesapp.model.Note
import com.example.notesapp.model.NoteProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object Repository {
    private var notesList: List<Note> = NoteProvider.modelNoteList
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

}
