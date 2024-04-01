package com.example.notesapp.presentation.adapter

import com.example.notesapp.domain.model.Note
import javax.inject.Inject

class NotesAdapterFactoryImpl @Inject constructor() : NotesAdapterFactory {
    override fun create(
        onClickListener: (Note) -> Unit,
        onDeleteListener: (Note) -> Unit
    ): NotesAdapter {
        return NotesAdapter(emptyList(), onClickListener, onDeleteListener)
    }
}