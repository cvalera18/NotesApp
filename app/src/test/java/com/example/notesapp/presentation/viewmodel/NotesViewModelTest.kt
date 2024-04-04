package com.example.notesapp.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.notesapp.domain.model.Note
import com.example.notesapp.domain.usecase.DeleteNoteUseCase
import com.example.notesapp.domain.usecase.GetNotesUseCase
import com.example.notesapp.domain.usecase.SearchNotesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class NotesViewModelTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: NotesViewModel

    @RelaxedMockK
    private lateinit var getNotesUseCase: GetNotesUseCase

    @RelaxedMockK
    private lateinit var searchNotesUseCase: SearchNotesUseCase

    @RelaxedMockK
    private lateinit var deleteNoteUseCase: DeleteNoteUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = NotesViewModel(getNotesUseCase, searchNotesUseCase, deleteNoteUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created at the first time, get all notes and show it`() = runTest {
        // Given
        val mockNotes = listOf(
            Note(1, "Título 1", "Contenido 1", System.currentTimeMillis()),
            Note(2, "Título 2", "Contenido 2", System.currentTimeMillis())
        )
        coEvery { getNotesUseCase() } returns mockNotes

        // When
        viewModel.getListNotes()

        // Then
        assert(viewModel.noteList.value == mockNotes)
    }
}