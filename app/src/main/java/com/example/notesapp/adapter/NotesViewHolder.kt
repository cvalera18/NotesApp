package com.example.notesapp.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.notesapp.databinding.ItemNoteBinding
import com.example.notesapp.model.Note

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
        binding.tvDate.text = noteModel.date
        binding.ivDelete.setOnClickListener {
            onDeleteListener.invoke(noteModel)
        }
        itemView.setOnClickListener {
            onClickListener(noteModel)
        }
    }
}
