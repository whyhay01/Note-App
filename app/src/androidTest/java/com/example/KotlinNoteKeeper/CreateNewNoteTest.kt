package com.example.KotlinNoteKeeper

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.Espresso.pressBack
import java.util.EnumSet.allOf


@RunWith(AndroidJUnit4::class)
class CreateNewNoteTest{

    @get:Rule
    val noteListActivity = ActivityScenarioRule(NoteListActivity::class.java)

    @Test
    fun createNewNote(){

        val course = DataManager.courses.get("android_intents")
        val noteTile = "This is the note title of our Automated test"
        val noteText = "This is the note text and the body of our Automated test"

        var action = onView(withId(R.id.fab))
        action.perform(click())

        action = onView(withId(R.id.spinner))
        action.perform(click())

        val dataAction = onData(allOf(instanceOf(CourseInfo::class.java), equalTo(course)))
        dataAction.perform(click())

        action = onView(withId(R.id.textNoteTitle))
        action.perform(typeText(noteTile))

        action = onView(withId(R.id.textNoteText)).perform(typeText(noteText), closeSoftKeyboard())
//        action.perform(typeText(noteText))

        pressBack()
//        pressBack()

        //Want to get the new note created and we know it's the last note in our notes package
        val newNote = DataManager.notes.last()

        assertEquals(course, newNote.course)
        assertEquals(noteTile, newNote.title)
        assertEquals(noteText, newNote.text)

    }

}