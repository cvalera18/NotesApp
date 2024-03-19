package com.example.notesapp.ui.newnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesapp.data.Repository
import com.example.notesapp.model.Note

class NewNoteViewModel: ViewModel() {

    private val repository = Repository
    private val _newNoteList = MutableLiveData<List<Note>>(emptyList())
    val newNoteList: LiveData<List<Note>> = _newNoteList
    fun saveOrUpdateNote(id: Long, title: String, body: String, date: String) {
        if (id <= 0) {
            repository.saveNote(title, body)
        } else {
            val updatedNote = Note(id, title, body, date)
            repository.updateNote(updatedNote)
        }
    }
}