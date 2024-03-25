package com.example.notesapp.ui.newnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesapp.data.repository.Repository
import com.example.notesapp.data.repository.RepositoryImpl
import com.example.notesapp.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewNoteViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
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