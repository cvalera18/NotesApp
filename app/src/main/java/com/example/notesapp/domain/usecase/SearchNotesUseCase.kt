package com.example.notesapp.domain.usecase

import com.example.notesapp.domain.model.Note
import com.example.notesapp.domain.repository.Repository

class SearchNotesUseCase (private val repository: Repository) {
    suspend operator fun invoke(query: String): List<Note> = repository.searchNotes(query)
}