package com.example.KotlinNoteKeeper

import androidx.core.content.MimeTypeFilter.matches
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.JUnitCore
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches

@RunWith(AndroidJUnit4::class)
class NextThroughNotes{

    @get:Rule
    val noteListActivity = ActivityScenarioRule(NoteListActivity::class.java)

    @Test
    fun nextThroughNotes(){
        val note = DataManager.notes[0]
        var action = onData(allOf(instanceOf(NoteInfo::class.java),equalTo(note)))
        action.perform(click())

        for (index in 0..DataManager.notes.lastIndex){
            val nextNote = DataManager.notes[index]

            onView(withId(R.id.spinner)).check(
                    matches(withSpinnerText(nextNote.course?.title)))

            onView(withId(R.id.textNoteTitle)).check(
                    matches(withText(nextNote.title)))

            onView(withId(R.id.textNoteText)).check(
                    matches(withText(nextNote.text)))

            //enable clicK on the next menu
            if (index != DataManager.notes.lastIndex) {
                onView(allOf(withId(R.id.action_next), isEnabled())).perform(click())
            }

        }

        onView(withId(R.id.action_next)).check(matches(isEnabled()))
    }

}