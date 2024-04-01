package com.example.notesapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.domain.repository.Repository
import com.example.notesapp.domain.model.Note
import com.example.notesapp.domain.usecase.SaveNoteUseCase
import com.example.notesapp.domain.usecase.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewNoteViewModel @Inject constructor(
    private val saveNoteUseCase: SaveNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
) : ViewModel() {
    private val _newNoteList = MutableLiveData<List<Note>>(emptyList())
    val newNoteList: LiveData<List<Note>> = _newNoteList
    suspend fun saveOrUpdateNote(id: Long, title: String, body: String, date: Long) {
        viewModelScope.launch {
            val note = Note(id, title, body, date)
            if (id == 0L) {
                saveNoteUseCase(note)
            } else {
                updateNoteUseCase(note)
            }
        }
    }
}