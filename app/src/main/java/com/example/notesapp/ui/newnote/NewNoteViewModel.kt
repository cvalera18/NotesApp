package com.example.notesapp.ui.newnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesapp.data.Repository
import com.example.notesapp.model.Note
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NewNoteViewModel: ViewModel() {

    private val repository = Repository
    private val _newNoteList = MutableLiveData<List<Note>>(emptyList())
    val newNoteList: LiveData<List<Note>> = _newNoteList

    fun saveNote(title: String, body: String) {
        val dateFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
        val currentDate = dateFormat.format(Date())
            repository.saveNote(title, body, currentDate)
    }
}