package com.fueled.reclaim.samples.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Created by hussein@fueled.com on 05/02/2019.
 */
object NotesStorage {

    private val allNotes = mutableListOf(
        Note(title = "Test Note 1", body = "Suspendisse accumsan posuere nibh. Proin leo dolor, scelerisque eu ultrices in, varius et dui."),
        Note(title = "Test Note 2", body = "Proin pulvinar nisi at elementum euismod. Sed aliquam blandit leo a semper. Fusce eget dolor sed ex venenatis interdum nec tincidunt odio."),
        Note(title = "Test Note 3", body = "Maecenas luctus ligula mauris, cursus tristique metus aliquam dapibus.")
    )

    private val allNotesLiveData = MutableLiveData<List<Note>>().apply {
        value = allNotes
    }

    fun getAllNotes(): LiveData<List<Note>> = allNotesLiveData

    fun addNote(note: Note) {
        allNotes.add(note)
        allNotesLiveData.value = allNotes
    }

    fun removeNote(noteId: String) {
        allNotes.removeAll { it.id == noteId }
        allNotesLiveData.value = allNotes
    }

}