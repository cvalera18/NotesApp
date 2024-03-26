package com.example.notesapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.repository.Repository
import com.example.notesapp.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
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

    suspend fun onDeleteNote(note: Note) {
        repository.onDeleteNote(note)
        getListNotes()
    }
}