package com.example.notesapp.domain.model

import com.example.notesapp.data.datasource.local.database.entities.NoteEntity

data class Note(
    val id: Long,
    val title: String,
    val body: String,
    val date: Long
)

fun Note.toEntity(): NoteEntity = NoteEntity(id = this.id, title = this.title, body = this.body, date = this.date)
fun NoteEntity.toNote(): Note = Note(id = this.id, title = this.title, body = this.body, date = this.date)