package com.example.notesapp.domain.usecase

import com.example.notesapp.domain.model.Note
import com.example.notesapp.domain.repository.Repository
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class UpdateNoteUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: Repository
    private lateinit var updateNoteUseCase: UpdateNoteUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        updateNoteUseCase = UpdateNoteUseCase(repository)
    }

    @Test
    fun `when updateNoteUseCase is invoked, repository's updateNote is called with the correct note`() =
        runTest {
            // Given
            val note = Note(
                id = 1L,
                title = "Updated Title",
                body = "Updated Body",
                System.currentTimeMillis()
            )

            // When
            updateNoteUseCase(note)

            // Then
            coVerify { repository.updateNote(note) }
        }
}