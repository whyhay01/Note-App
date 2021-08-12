package com.example.KotlinNoteKeeper

import android.content.Intent
import android.icu.lang.UCharacter
import android.icu.lang.UCharacter.VerticalOrientation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.HorizontalScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
            val fabIntent = Intent(this, NoteActivity::class.java)
            startActivity(fabIntent)
        })

        //Specifying RecyclerView LayoutManager
        val displayLayout = LinearLayoutManager(this)
//        val gridLayout = GridLayoutManager(this, 3)
        binding?.listItems?.layoutManager = displayLayout

        //Setting up NoteListAdapter on the recyclerView
        val recyclerViewAdapter = NoteListAdapter(this, DataManager.notes)
        binding?.listItems?.adapter = recyclerViewAdapter

    }

    override fun onResume() {
        super.onResume()
        binding?.listItems?.adapter?.notifyDataSetChanged()
    }
}