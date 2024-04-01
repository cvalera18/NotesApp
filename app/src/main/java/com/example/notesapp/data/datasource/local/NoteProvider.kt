package com.example.notesapp.data.datasource.local

import com.example.notesapp.domain.model.Note
import javax.inject.Inject

class NoteProvider @Inject constructor() {

    val modelNoteList = listOf(
        Note(
            id = 1,
            title = "Primera Notita",
            body = "Este es un modelo de nota, la idea es ver si se muestra bien, primero con datos locales antes de guardar realmente en una BD. En fecha colocaremos una palabra",
            date = "Yesterday".toLong()
        ),
        Note(
            id = 2,
            title = "Segunda Nota",
            body = "Esta debería ser la segunda nota, la idea es ver si se muestra bien, en este como fecha colocaré mi cumpleaños",
            date = "18/12".toLong()
        ),
        Note(
            id = 3,
            title = "Tercera Nota",
            body = "Texto de prueba, este un poco más corto",
            date = "08/01".toLong()
        ),
        Note(
            id = 4,
            title = "Cuarta Nota",
            body = "Este es un modelo de nota, con la palabra Ripsy la idea es ver si se muestra bien, primero con datos locales antes de guardar realmente en una BD. En fecha colocaremos una palabra",
            date = "Yesterday".toLong()
        ),
        Note(
            id = 5,
            title = "Quinta Nota",
            body = "Esta debería ser la segunda nota, la idea es ver si se muestra bien, en este como fecha colocaré mi cumpleaños",
            date = "18/12".toLong()
        ),
        Note(
            id = 6,
            title = "Sexta Nota",
            body = "Texto de prueba, este un poco más corto",
            date = "08/01".toLong()
        ),
        Note(
            id = 7,
            title = "Séptima Nota",
            body = "Este es un modelo de nota, la idea es ver si se muestra bien, primero con datos locales antes de guardar realmente en una BD. En fecha colocaremos una palabra",
            date = "Yesterday".toLong()
        ),
        Note(
            id = 8,
            title = "Octava Nota",
            body = "Esta debería ser la segunda nota, la idea es ver si se muestra bien, en este como fecha colocaré mi cumpleaños",
            date = "18/12".toLong()
        ),
        Note(
            id = 9,
            title = "Novena Nota",
            body = "Texto de prueba, este un poco más corto",
            date = "08/01".toLong()
        )
    )
}