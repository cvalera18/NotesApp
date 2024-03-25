package com.example.notesapp.adapter

import com.example.notesapp.model.Note

interface NotesAdapterFactory {
    fun create(onClickListener: (Note) -> Unit, onDeleteListener: (Note) -> Unit): NotesAdapter
}