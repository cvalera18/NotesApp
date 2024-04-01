package com.example.notesapp.domain.usecase

import com.example.notesapp.domain.model.Note
import com.example.notesapp.domain.repository.Repository

class GetNotesUseCase(private val repository: Repository) {
    suspend operator fun invoke(): List<Note> = repository.getNotes()
}