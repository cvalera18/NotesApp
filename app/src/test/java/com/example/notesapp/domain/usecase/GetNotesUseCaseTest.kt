package com.example.notesapp.domain.usecase

import com.example.notesapp.domain.model.Note
import com.example.notesapp.domain.repository.Repository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNotesUseCaseTest{

    @RelaxedMockK
    private lateinit var repository: Repository

    lateinit var getNotesUseCase: GetNotesUseCase
    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getNotesUseCase = GetNotesUseCase(repository)
    }

    @Test
    fun `when getNotes is called and repository returns empty list`() = runBlocking {
        //Given
        coEvery { repository.getNotes() } returns emptyList()

        //When
        getNotesUseCase()

        //Then
        coVerify(exactly = 1) { repository.getNotes() }
    }

    @Test
    fun `when getNotes is called and repository returns non-empty list`() = runBlocking {
        // Given
        val mockNotes = listOf(Note(1, "Title", "Content", System.currentTimeMillis()))
        coEvery { repository.getNotes() } returns mockNotes

        // When
        val result = getNotesUseCase()

        // Then
        coVerify(exactly = 1) { repository.getNotes() }
        assertEquals(mockNotes, result)
    }

}