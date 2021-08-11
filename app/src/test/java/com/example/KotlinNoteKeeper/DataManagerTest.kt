package com.example.KotlinNoteKeeper

import android.provider.ContactsContract
import org.junit.Assert.*

class DataManagerTest {

    @org.junit.Test
    fun addNewNote() {

        val course = DataManager.courses.get("android_async")!!
        val noteTile = "new note"
        val noteText ="This is a new note"

        val index = DataManager.addNewNote(course, noteTile, noteText)
        val note = DataManager.notes[index]

        assertEquals(course, note.course)
        assertEquals(noteText, note.text)
        assertEquals(noteTile, note.title)
    }

    @org.junit.Test
    fun searchNote(){
        val course = DataManager.courses.get("android_async")!!
        val noteTitle = "new note"
        val noteText1 = "This is a new note"
        val noteText2 = "This is the note text for the second text "

        //Getting the index of the two notes

        val index1 = DataManager.addNewNote(course,noteTitle,noteText1)
        val index2 =  DataManager.addNewNote(course, noteTitle, noteText2)

        // Try to find a note and get the index of the note returned,
    // the compare it with the supposed index

        val foundNote1 = DataManager.searchNote(course,noteText1, noteTitle)
        val indexOfFoundNote1 = DataManager.notes.indexOf(foundNote1)
        assertEquals(index1, indexOfFoundNote1)

        val foundNote2 = DataManager.searchNote(course, noteText2, noteTitle)
        val indexOfFoundNote2 = DataManager.notes.indexOf(foundNote2)
        assertEquals(index2, indexOfFoundNote2)

    }
}