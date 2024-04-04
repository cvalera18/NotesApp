package com.example.notesapp.domain.usecase

import com.example.notesapp.domain.model.Note
import com.example.notesapp.domain.repository.Repository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.just
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class DeleteNoteUseCaseTest {
    // Mock del repositorio
    @RelaxedMockK
    private lateinit var repository: Repository
    private lateinit var deleteNoteUseCase: DeleteNoteUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        deleteNoteUseCase = DeleteNoteUseCase(repository)
    }

    @Test
    fun `invoke deleteNoteUseCase, verify onDeleteNote called on repository with correct note`() =
        runBlocking {
            // Given
            val note = Note(
                id = 0,
                title = "Test Title",
                body = "Test Body",
                date = System.currentTimeMillis()
            )

            // When
            deleteNoteUseCase(note)

            // Then
            coVerify(exactly = 1) { repository.onDeleteNote(note) }
        }

}