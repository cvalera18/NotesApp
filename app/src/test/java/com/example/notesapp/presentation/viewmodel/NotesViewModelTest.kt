package com.example.notesapp.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.notesapp.domain.model.Note
import com.example.notesapp.domain.usecase.DeleteNoteUseCase
import com.example.notesapp.domain.usecase.GetNotesUseCase
import com.example.notesapp.domain.usecase.SearchNotesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

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
        assertEquals(mockNotes, viewModel.noteList.getOrAwaitValue())
    }

    @Test
    fun `when searchNotes is called, filtered notes are updated`() = runTest {
        // Given
        val userFilter = "Test"
        val filteredNotes = listOf(
            Note(1, "Test Note 1", "Content 1", System.currentTimeMillis()),
            Note(2, "Test Note 2", "Content 2", System.currentTimeMillis())
        )
        coEvery { searchNotesUseCase(userFilter) } returns filteredNotes

        // When
        viewModel.searchNotes(userFilter)

        // Then
        assertEquals(filteredNotes, viewModel.noteList.getOrAwaitValue())
    }

    @Test
    fun `when onDeleteNote is called, note is deleted and list is updated`() = runTest {
        // Given
        val noteToDelete = Note(1, "Note to Delete", "Content", System.currentTimeMillis())
        val remainingNotes = listOf(
            Note(2, "Remaining Note 1", "Content", System.currentTimeMillis())
        )
        coJustRun { deleteNoteUseCase(noteToDelete) }
        coEvery { getNotesUseCase() } returns remainingNotes

        // When
        viewModel.onDeleteNote(noteToDelete)

        // Then
        coVerify(exactly = 1) { deleteNoteUseCase(noteToDelete) }
        assertEquals(remainingNotes, viewModel.noteList.getOrAwaitValue())
    }

}

fun <T> LiveData<T>.getOrAwaitValue(): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {

        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)
    latch.await(2, TimeUnit.SECONDS)

    @Suppress("UNCHECKED_CAST")
    return data as T
}
