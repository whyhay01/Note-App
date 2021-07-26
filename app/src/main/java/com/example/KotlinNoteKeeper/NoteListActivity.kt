package com.example.KotlinNoteKeeper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.KotlinNoteKeeper.databinding.ActivityNoteListBinding

class NoteListActivity : AppCompatActivity() {

    var binding : ActivityNoteListBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        binding = ActivityNoteListBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //implementing Fab
        binding?.fab?.setOnClickListener(View.OnClickListener {
            val fabIntent = Intent(this, MainActivity::class.java)
            startActivity(fabIntent)
        })

        //populating the listview
        binding?.listView?.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,DataManager.notes)

        //Intent with putExtra
        binding?.listView?.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(NOTE_POSITION, position)
            startActivity(intent)
        })
    }

    override fun onResume() {
        super.onResume()
        (binding?.listView?.adapter as ArrayAdapter<NoteInfo>).notifyDataSetChanged()
    }
}