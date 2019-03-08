package com.fueled.reclaim.samples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.fueled.reclaim.samples.notes.Note
import com.fueled.reclaim.samples.notes.NotesStorage
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar

/**
 * Created by hussein@fueled.com on 07/03/2019.
 */
class AddNoteDialog : BottomSheetDialogFragment() {

    private lateinit var titleEditText: EditText
    private lateinit var bodyEditText: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_add_note, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titleEditText = view.findViewById(R.id.title_edit_text)
        bodyEditText = view.findViewById(R.id.note_edit_text)

        view.findViewById<View>(R.id.save_button).setOnClickListener {
            val title = titleEditText.text.toString()
            val body = bodyEditText.text.toString()

            if (title.isNotBlank() && body.isNotBlank()) {
                NotesStorage.addNote(Note(title = title, body = body))
                dismiss()
            } else {
                Snackbar
                    .make(view, "Please make sure all fields are filled in.", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

}