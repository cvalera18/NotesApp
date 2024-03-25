package com.example.notesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.model.Note
import javax.inject.Inject

class NotesAdapter(
    private var noteList: List<Note>,
    private var onClickListener: (Note) -> Unit,
    private var onDeleteListener: (Note) -> Unit
) : RecyclerView.Adapter<NotesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NotesViewHolder(
            layoutInflater.inflate(R.layout.item_note, parent, false), this
        )
    }

    override fun getItemCount(): Int = noteList.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val item = noteList[position]
        holder.render(
            item, onClickListener, onDeleteListener
        )
    }

    fun updateNotes(noteList: List<Note>) {
        this.noteList = noteList
        notifyDataSetChanged()
    }

}