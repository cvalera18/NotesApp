package com.example.notesapp.ui.newnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentNewNoteBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class NewNoteFragment : Fragment() {
    private var _binding: FragmentNewNoteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewNoteViewModel by viewModels()
    private var currentId: Long = 0

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
        val date = arguments?.getLong("DATE", 0L) ?: 0L
        currentId = arguments?.getString("ID")?.toLong() ?:0
        binding.etTitleNote.setText(title)
        binding.etBodyNote.setText(body)
        binding.tvNewNoteDate.text = if (date != 0L) {
            SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(Date(date))
        } else {
            SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(Date())
        }
    }

    private fun saveNote() {
        val fab: FloatingActionButton = binding.fabSaveNote
        fab.setOnClickListener {
            val title = binding.etTitleNote.text.toString()
            val body = binding.etBodyNote.text.toString()
            val date = binding.tvNewNoteDate.text.toString()
            lifecycleScope.launch {
                val dateAsLong = convertDateStringToLong(date)
                viewModel.saveOrUpdateNote(currentId, title, body, dateAsLong)
                findNavController().navigate(R.id.action_newNoteFragment_to_notesFragment)
            }
        }
    }

    private fun convertDateStringToLong(dateString: String): Long {
        val dateFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
        return try {
            val date = dateFormat.parse(dateString)
            date?.time ?: System.currentTimeMillis() // Usa la fecha actual como fallback si el parsing falla
        } catch (e: Exception) {
            System.currentTimeMillis() // Usa la fecha actual como fallback en caso de error
        }
    }
}