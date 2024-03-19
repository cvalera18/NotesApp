package com.example.notesapp.model

data class Note(
    var id: Long,
    val title: String,
    val body: String,
    val date: String
)