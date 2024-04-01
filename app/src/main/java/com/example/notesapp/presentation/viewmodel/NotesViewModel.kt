package com.example.notesapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.domain.model.Note
import com.example.notesapp.domain.usecase.DeleteNoteUseCase
import com.example.notesapp.domain.usecase.GetNotesUseCase
import com.example.notesapp.domain.usecase.SearchNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val searchNotesUseCase: SearchNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {
    private val _noteList = MutableLiveData<List<Note>>(emptyList())
    val noteList: LiveData<List<Note>> = _noteList

    fun getListNotes() {
        viewModelScope.launch {
            val notes = getNotesUseCase()
            _noteList.value = notes
        }
    }

    fun searchNotes(userFilter: String) {
        viewModelScope.launch {
            val filteredNotes = searchNotesUseCase(userFilter)
            _noteList.value = filteredNotes
        }
    }

    suspend fun onDeleteNote(note: Note) {
        deleteNoteUseCase(note)
        getListNotes()
    }
}