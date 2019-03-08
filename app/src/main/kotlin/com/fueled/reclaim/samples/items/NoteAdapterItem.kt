package com.fueled.reclaim.samples.items

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.fueled.reclaim.AdapterItem
import com.fueled.reclaim.BaseViewHolder
import com.fueled.reclaim.samples.R
import com.fueled.reclaim.samples.bindView
import com.fueled.reclaim.samples.notes.Note
import com.google.android.material.card.MaterialCardView

/**
 * Created by hussein@fueled.com on 05/02/2019.
 */
class NoteAdapterItem(
    val note: Note,
    val backgroundColor: Int,
    val deleteNoteAction: (note: Note) -> Unit
) : AdapterItem<NoteViewHolder>() {

    override val layoutId = R.layout.item_note

    override fun onCreateViewHolder(view: View) = NoteViewHolder(view)

    override fun updateItemViews(viewHolder: NoteViewHolder) {
        viewHolder.apply {
            titleTextView.text = note.title
            bodyTextView.text = note.body
            containerCard.setCardBackgroundColor(backgroundColor)

            deleteNoteButton.setOnClickListener { deleteNoteAction(note) }
        }
    }

    override fun isTheSame(newItem: AdapterItem<*>): Boolean {
        return newItem is NoteAdapterItem && newItem.note.id == note.id
    }

    override fun isContentsTheSame(newItem: AdapterItem<*>): Boolean {
        return newItem is NoteAdapterItem && newItem.note == note
            && newItem.backgroundColor == backgroundColor
    }
}

class NoteViewHolder(view: View) : BaseViewHolder(view) {
    val containerCard: MaterialCardView = bindView(R.id.container_card)
    val titleTextView: TextView = bindView(R.id.title_text_view)
    val bodyTextView: TextView = bindView(R.id.body_text_view)
    val deleteNoteButton: ImageButton = bindView(R.id.delete_note_button)
}