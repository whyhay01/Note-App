package com.example.KotlinNoteKeeper

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import com.example.KotlinNoteKeeper.databinding.ActivityNoteBinding

class NoteActivity : AppCompatActivity() {

    private var notePosition:Int = POSITION_NOT_SET

    var binding: ActivityNoteBinding? = null

    private val Tag = this::class.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        //Implementing viewBinding
        binding = ActivityNoteBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

        //Populating a spinner
        val courses = ArrayList<CourseInfo>(DataManager.courses.values)
        val adapterCourses = ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
        courses)

        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding?.spinner?.adapter = adapterCourses

        //SETTING UP EXTRAS and SavedInstances
        notePosition = savedInstanceState?.getInt(NOTE_POSITION, POSITION_NOT_SET) ?:
                intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET)

        if (notePosition != POSITION_NOT_SET)
            displayNote()
        else{
            DataManager.notes.add(NoteInfo())
            notePosition = DataManager.notes.lastIndex
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putInt(NOTE_POSITION,notePosition)
    }

    private fun displayNote() {
       if(notePosition > DataManager.notes.lastIndex){
        showMessage("NOTE NOT FOUND")
           return
       }

        val note = DataManager.notes[notePosition]
        binding?.textNoteTitle?.setText(note.title)
        binding?.textNoteText?.setText(note.text)

        val coursePosition =DataManager.courses.values.indexOf(note.course)
        binding?.spinner?.setSelection(coursePosition)

    }

    private fun showMessage(message: String) {
        val showContextView = findViewById<View>(R.id.view)
        Snackbar.make(showContextView, message,Snackbar.LENGTH_LONG).show()
        
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
            R.id.action_next -> {
                if (notePosition < DataManager.notes.lastIndex) {
                    saveNote()
                    moveNext()
                }else{
                    val message = "No more notes"
                    showMessage(message)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun moveNext() {
        ++notePosition
        displayNote()
        invalidateOptionsMenu()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (notePosition >= DataManager.notes.lastIndex){
            val menuItem = menu?.findItem(R.id.action_next)
            if (menuItem != null) {
                menuItem?.icon = getDrawable(R.drawable.ic_block_white_24)
            }
//            menuItem?.isEnabled = false
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    private fun saveNote() {
        val note = DataManager.notes[notePosition]
        note.text = binding?.textNoteText?.text.toString()
        note.title = binding?.textNoteTitle?.text.toString()
        note.course = binding?.spinner?.selectedItem as CourseInfo
    }
}