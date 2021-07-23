package com.example.KotlinNoteKeeper

import android.os.Bundle
import android.service.voice.VoiceInteractionSession
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.view.accessibility.AccessibilityEventCompat.setAction
import com.example.KotlinNoteKeeper.databinding.ActivityMainBinding as ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val POSITION_NOT_SET = -1

    var notePosition:Int = 0

    var binding: ActivityMainBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        //Implementing viewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

        //Populating a spinner
        val courses = ArrayList<CourseInfo>(DataManager.courses.values)
        val adapterCourses = ArrayAdapter<CourseInfo>(this,
                android.R.layout.simple_spinner_item,
        courses)

        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding?.spinner?.adapter = adapterCourses

        //SETTING UP EXTRAS
        notePosition = intent.getIntExtra("item_position", POSITION_NOT_SET)

        if (notePosition != POSITION_NOT_SET)
            displayNote()
    }

    private fun displayNote() {
        val note = DataManager.notes[notePosition]
        binding?.textNoteTitle?.setText(note.title)
        binding?.textNoteText?.setText(note.text)

        val coursePosition =DataManager.courses.values.indexOf(note.course)
        binding?.spinner?.setSelection(coursePosition)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}