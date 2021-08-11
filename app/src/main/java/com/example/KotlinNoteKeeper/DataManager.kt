package com.example.KotlinNoteKeeper

object DataManager {
    //adding data manager properties
    //We need a collection holder where we can store the collection of our courseInfo the we will be using hashmap
    // because it provides...
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        initializeCourse()
        initializeNotes()
    }

    fun addNewNote(course: CourseInfo, noteTitle: String, noteText: String): Int{

        val newNote = NoteInfo(course, noteTitle, noteText)
        notes.add(newNote)
        val notePosition = notes.lastIndex

        return notePosition
    }

    fun searchNote(course: CourseInfo, noteText: String, noteTitle: String): NoteInfo?{
        for (note in notes){
            if (course == note.course && noteText == note.text && noteTitle == note.title)
                return note
        }
        return null
    }

    private fun initializeCourse(){
        var course = CourseInfo("android_intents", "Android Programming with Intent")
        courses.set("android_intents",course)

        course = CourseInfo("android_async","Android Async and Services")
        courses.set("android_async", course)

        course = CourseInfo("java_lang", "Java Fundamental: The Java Language")
        courses.set("java_lang", course)

        course = CourseInfo("java_core", "Java Fundamental: The core Platform")
        courses.set("java_core",course)
    }


    private fun initializeNotes() {

        var course = courses["android_intents"]!!
        var note = NoteInfo(course, "Dynamic intent resolution",
            "Wow, intents allow components to be resolved at runtime")
        notes.add(note)
        note = NoteInfo(course, "Delegating intents",
            "Pending Intents are powerful; they delegate much more than just a component invocation")
        notes.add(note)

        course = courses["android_async"]!!
        note = NoteInfo(course, "Service default threads",
            "Did you know that by default an Android Service will tie up the UI thread?")
        notes.add(note)
        note = NoteInfo(course, "Long running operations",
            "Foreground Services can be tied to a notification icon")
        notes.add(note)

        course = courses["java_lang"]!!
        note = NoteInfo(course, "Parameters",
            "Leverage variable-length parameter lists")
        notes.add(note)
        note = NoteInfo(course, "Anonymous classes",
            "Anonymous classes simplify implementing one-use types")
        notes.add(note)

        course = courses["java_core"]!!
        note = NoteInfo(course, "Compiler options",
            "The -jar option isn't compatible with with the -cp option")
        notes.add(note)
        note = NoteInfo(course, "Serialization",
            "Remember to include SerialVersionUID to assure version compatibility")
        notes.add(note)
    }
}