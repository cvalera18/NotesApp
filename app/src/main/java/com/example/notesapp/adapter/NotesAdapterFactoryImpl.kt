package com.example.notesapp.adapter

import com.example.notesapp.model.Note
import javax.inject.Inject

class NotesAdapterFactoryImpl @Inject constructor() : NotesAdapterFactory {
    override fun create(
        onClickListener: (Note) -> Unit,
        onDeleteListener: (Note) -> Unit
    ): NotesAdapter {
        return NotesAdapter(emptyList(), onClickListener, onDeleteListener)
    }
}