package com.example.notesapp.presentation.adapter

import com.example.notesapp.domain.model.Note

interface NotesAdapterFactory {
    fun create(onClickListener: (Note) -> Unit, onDeleteListener: (Note) -> Unit): NotesAdapter
}