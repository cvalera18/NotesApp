package com.example.notesapp.domain.usecase

import com.example.notesapp.domain.model.Note
import com.example.notesapp.domain.repository.Repository
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class SaveNoteUseCaseTest {

    private lateinit var saveNoteUseCase: SaveNoteUseCase
    private val repository: Repository = mock(Repository::class.java)

    @Before
    fun setUp() {
        saveNoteUseCase = SaveNoteUseCase(repository)
    }

    @Test
    fun `save note calls repository`() = runTest {
        val note = Note(0, "Test title", "Test body", System.currentTimeMillis())
        saveNoteUseCase(note)

        verify(repository).saveNote(note.title, note.body)
    }
}

