package com.example.notesapp.ui.newnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentNewNoteBinding
import com.example.notesapp.model.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NewNoteFragment : Fragment() {
    private var _binding: FragmentNewNoteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewNoteViewModel by viewModels()
    private var currentId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveNote()
        initInfo()
        navigateBack()
    }

    private fun navigateBack() {
        binding.buttonBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initInfo() {
        val title = arguments?.getString("TITLE")
        val body = arguments?.getString("BODY")
        binding.etTitleNote.setText(title)
        binding.etBodyNote.setText(body)
    }

    private fun saveNote() {
        val fab: FloatingActionButton = binding.fabSaveNote
        fab.setOnClickListener {
            val title = binding.etTitleNote.text.toString()
            val body = binding.etBodyNote.text.toString()
            viewModel.saveNote(title, body)
            findNavController().navigate(R.id.action_newNoteFragment_to_notesFragment)
        }
    }
}