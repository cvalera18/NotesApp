package com.example.notesapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesapp.domain.repository.Repository
import com.example.notesapp.domain.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewNoteViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _newNoteList = MutableLiveData<List<Note>>(emptyList())
    val newNoteList: LiveData<List<Note>> = _newNoteList
    suspend fun saveOrUpdateNote(id: Long, title: String, body: String, date: Long) {
        if (id <= 0) {
            repository.saveNote(title, body)
        } else {
            val updatedNote = Note(id, title, body, date)
            repository.updateNote(updatedNote)
        }
    }
}