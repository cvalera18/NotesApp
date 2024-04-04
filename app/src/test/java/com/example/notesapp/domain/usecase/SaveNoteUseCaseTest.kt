package com.example.notesapp.domain.usecase

import com.example.notesapp.domain.model.Note
import com.example.notesapp.domain.repository.Repository
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SaveNoteUseCaseTest {

    // Mock del repositorio
    @RelaxedMockK
    private lateinit var repository: Repository
    private lateinit var saveNoteUseCase: SaveNoteUseCase

    @Before
    fun setUp() {
        // Inicializar el mock del Repository con MockK
        MockKAnnotations.init(this)
        // Inicializar el SaveNoteUseCase con el repositorio mockeado
        saveNoteUseCase = SaveNoteUseCase(repository)
    }

    @Test
    fun `invoke saveNoteUseCase, verify saveNote called on repository with correct arguments`() =
        runTest {
            // Given
            val note = Note(
                id = 0,
                title = "Test Title",
                body = "Test Body",
                date = System.currentTimeMillis()
            )

            // When
            saveNoteUseCase(note)

            // Then
            coVerify(exactly = 1) { repository.saveNote(note.title, note.body) }
        }
}