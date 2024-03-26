package com.example.notesapp.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.notesapp.databinding.ItemNoteBinding
import com.example.notesapp.model.Note
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NotesViewHolder(
    view: View,
    private val notesAdapter: NotesAdapter
) : ViewHolder(view) {

    private val binding = ItemNoteBinding.bind(view)

    fun render(
        noteModel: Note,
        onClickListener: (Note) -> Unit,
        onDeleteListener: (Note) -> Unit
    ) {
        binding.tvTitle.text = noteModel.title
        binding.tvNoteBody.text = noteModel.body
        binding.tvDate.text = SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(Date(noteModel.date))
        binding.ivDelete.setOnClickListener {
            onDeleteListener.invoke(noteModel)
        }
        itemView.setOnClickListener {
            onClickListener(noteModel)
        }
    }
}
