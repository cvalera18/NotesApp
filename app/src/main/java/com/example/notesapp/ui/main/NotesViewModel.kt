package com.example.notesapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesapp.data.Repository
import com.example.notesapp.model.Note
import com.example.notesapp.model.NoteProvider

class NotesViewModel : ViewModel() {
    private val repository = Repository
    private val _noteList = MutableLiveData<List<Note>>(emptyList())
    val noteList: LiveData<List<Note>> = _noteList

    fun getListNotes() {
        _noteList.value = NoteProvider.modelNoteList
    }

}