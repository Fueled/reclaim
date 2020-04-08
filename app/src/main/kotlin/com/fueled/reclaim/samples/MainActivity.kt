package com.fueled.reclaim.samples

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.fueled.reclaim.ItemsViewAdapter
import com.fueled.reclaim.samples.items.HeaderAdapterItem
import com.fueled.reclaim.samples.items.NoteAdapterItem
import com.fueled.reclaim.samples.items.LoadingAdapterItem
import com.fueled.reclaim.samples.notes.Note
import com.fueled.reclaim.samples.notes.NotesStorage


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = ItemsViewAdapter()

    private val cardColors = listOf(0xffe1f5fe, 0xfffffde7, 0xfffbe9e7, 0xffefebe9, 0xfff3e5f5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)

        findViewById<View>(R.id.add_button).setOnClickListener {
            AddNoteDialog().show(supportFragmentManager, "ADD_NOTE_DIALOG")
        }

        val layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)

        recyclerView.layoutManager = layoutManager

        //set adapter
        recyclerView.adapter = adapter

        subscribeToNoteUpdates()
    }

    private fun subscribeToNoteUpdates() {
        NotesStorage.getAllNotes()
            .observe(this, Observer {
                displayNotes(it)
            })
    }

    private fun displayNotes(notes: List<Note>) {
        val items = listOf(HeaderAdapterItem("Elliot Alderson")) + notes
            .mapIndexed { index, note ->
                NoteAdapterItem(note, cardColors[index % cardColors.size].toInt(), ::onDeleteNote)
            } + listOf(LoadingAdapterItem())

        adapter.replaceItems(
            items = items,
            useDiffCheck = true
        )
    }

    private fun onDeleteNote(note: Note) {
        NotesStorage.removeNote(note.id)
    }
}
