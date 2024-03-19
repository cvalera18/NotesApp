package com.example.notesapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.Repository
import com.example.notesapp.model.Note
import com.example.notesapp.model.NoteProvider
import kotlinx.coroutines.launch

class NotesViewModel : ViewModel() {
    private val repository = Repository
    private val _noteList = MutableLiveData<List<Note>>(emptyList())
    val noteList: LiveData<List<Note>> = _noteList

    fun getListNotes() {
        viewModelScope.launch {
            _noteList.value = repository.getNotes()
        }
    }

    fun searchNotes(userFilter: String) {
        viewModelScope.launch {
            val filteredNotes = repository.searchNotes(userFilter)
            _noteList.value = filteredNotes
        }
    }

}