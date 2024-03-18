package com.example.notesapp.model

class NoteProvider {

    companion object {
        val modelNoteList = listOf(
            Note(
                id = 1,
                title = "Primera Nota",
                body = "Este es un modelo de nota, la idea es ver si se muestra bien, primero con datos locales antes de guardar realmente en una BD. En fecha colocaremos una palabra",
                date = "Yesterday"
            ),
            Note(
                id = 2,
                title = "Segunda Nota",
                body = "Esta debería ser la segunda nota, la idea es ver si se muestra bien, en este como fecha colocaré mi cumpleaños",
                date = "18/12"
            ),
            Note(
                id = 3,
                title = "Tercera Nota",
                body = "Texto de prueba, este un poco más corto",
                date = "08/01"
            )
        )
    }
}