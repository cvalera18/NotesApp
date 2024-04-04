package com.example.notesapp.domain.usecase

import com.example.notesapp.domain.model.Note
import com.example.notesapp.domain.repository.Repository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals

import org.junit.Before
import org.junit.Test

class SearchNotesUseCaseTest{

    @RelaxedMockK
    private lateinit var repository: Repository
    lateinit var searchNotesUseCase: SearchNotesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        searchNotesUseCase = SearchNotesUseCase(repository)
    }

    @Test
    fun `when repository returns notes, searchNotesUseCase returns the same notes`() = runBlocking {
        // Given
        val mockQuery = "test"
        val mockNotes = listOf(Note(1, "Test title", "Test body", System.currentTimeMillis()))
        coEvery { repository.searchNotes(mockQuery) } returns mockNotes

        // When
        val result = searchNotesUseCase(mockQuery)

        // Then
        assertEquals(mockNotes, result)
        coVerify(exactly = 1) { repository.searchNotes(mockQuery) }
    }

    @Test
    fun `when repository returns empty list, searchNotesUseCase returns empty list`() = runBlocking {
        // Given
        val mockQuery = "nothing"
        coEvery { repository.searchNotes(mockQuery) } returns emptyList()

        // When
        val result = searchNotesUseCase(mockQuery)

        // Then
        assert(result.isEmpty()) { "The result should be an empty list." }
        coVerify(exactly = 1) { repository.searchNotes(mockQuery) }
    }
}