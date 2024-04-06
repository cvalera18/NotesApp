package com.example.notesapp.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.notesapp.domain.model.Note
import com.example.notesapp.domain.usecase.SaveNoteUseCase
import com.example.notesapp.domain.usecase.UpdateNoteUseCase
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NewNoteViewModelTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: NewNoteViewModel

    @RelaxedMockK
    private lateinit var saveNoteUseCase: SaveNoteUseCase

    @RelaxedMockK
    private lateinit var updateNoteUseCase: UpdateNoteUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = NewNoteViewModel(saveNoteUseCase, updateNoteUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when saveOrUpdateNote is called with id 0, saveNoteUseCase is invoked`() = runTest {
        // Given
        val id = 0L
        val title = "New Note Title"
        val body = "New Note Body"
        val date = System.currentTimeMillis()
        val note = Note(id, title, body, date)

        // When
        viewModel.saveOrUpdateNote(id, title, body, date)

        // Then
        coVerify(exactly = 1) { saveNoteUseCase(note) }
    }

    @Test
    fun `when saveOrUpdateNote is called with non-zero id, updateNoteUseCase is invoked`() = runTest {
        // Given
        val id = 1L // Un ID no cero
        val title = "Updated Note Title"
        val body = "Updated Note Body"
        val date = System.currentTimeMillis()
        val note = Note(id, title, body, date)

        // When
        viewModel.saveOrUpdateNote(id, title, body, date)

        // Then
        coVerify(exactly = 1) { updateNoteUseCase(note) }
    }

}