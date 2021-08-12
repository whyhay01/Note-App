package com.example.KotlinNoteKeeper

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class NoteListAdapter(private val context: Context, private val notes: List<NoteInfo>):
        RecyclerView.Adapter<NoteListAdapter.NoteListViewHolder>() {
    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        val itemView = layoutInflater.inflate(R.layout.note_list_item,parent,false)
        return NoteListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        val note = notes[position]
        holder.noteCourse.text = note.course?.title
        holder.noteTitle.text = note.title
        holder.displayPosition = position
    }

    override fun getItemCount() = notes.size

    inner class NoteListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val noteCourse = itemView.findViewById<TextView>(R.id.noteCourse)
        val noteTitle = itemView.findViewById<TextView>(R.id.noteTitle)
        var displayPosition: Int = 0

        init {
            itemView.setOnClickListener {
                val intent = Intent(context, NoteActivity::class.java)
                intent.putExtra(NOTE_POSITION, displayPosition)
                context.startActivity(intent)
            }
        }

    }
}