package com.example.notesapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.R
import com.example.notesapp.adapter.NotesAdapter
import com.example.notesapp.databinding.FragmentNotesBinding
import com.example.notesapp.model.Note
import com.example.notesapp.model.NoteProvider

class NotesFragment : Fragment() {
    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: NotesAdapter
    private val viewModel: NotesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchNotes()
        initRecyclerView()
        observeNoteList()
        getListNotes()

    }

    private fun getListNotes() {
        viewModel.getListNotes()
    }
    private fun observeNoteList() {
        viewModel.noteList.observe(viewLifecycleOwner) { noteList ->
            adapter.updateNotes(noteList)
        }
    }
    private fun initRecyclerView() {
        adapter = NotesAdapter(
            noteList = emptyList(),
            onClickListener = { onItemSelected(it) },
            onDeleteListener = { onItemDeleted(it) },
        )
        val llmanager = LinearLayoutManager(requireContext())
        binding.recyclerNotesList.layoutManager = llmanager
        binding.recyclerNotesList.adapter = adapter
    }

    private fun onItemDeleted(note: Note) {
        viewModel.onDeleteNote(note)
    }

    private fun onItemSelected(note: Note) {

    }

    private fun searchNotes() {
        binding.etSearchNotes.addTextChangedListener{ userSearch ->
            viewModel.searchNotes(userSearch.toString())
        }
    }

}