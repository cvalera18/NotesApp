package com.example.notesapp.ui.newnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentNewNoteBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NewNoteFragment : Fragment() {
    private var _binding: FragmentNewNoteBinding? = null
    private val binding get() = _binding!!

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
    }

    private fun saveNote() {
        val fab: FloatingActionButton = binding.fabSaveNote
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_newNoteFragment_to_notesFragment)
        }
    }
}