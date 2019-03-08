package com.fueled.reclaim.samples.items

import android.view.View
import android.widget.TextView
import com.fueled.reclaim.AdapterItem
import com.fueled.reclaim.BaseViewHolder
import com.fueled.reclaim.samples.bindView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fueled.reclaim.samples.R


/**
 * Created by hussein@fueled.com on 05/02/2019.
 */
class HeaderAdapterItem(
    private val userName: String
) : AdapterItem<HeaderViewHolder>() {

    override val layoutId = R.layout.item_header

    override fun onCreateViewHolder(view: View) = HeaderViewHolder(view)

    override fun updateItemViews(viewHolder: HeaderViewHolder) {
        (viewHolder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams).isFullSpan = true

        viewHolder.nameTextView.text = userName
    }

    override fun isTheSame(newItem: AdapterItem<*>) = newItem is HeaderAdapterItem

    override fun isContentsTheSame(newItem: AdapterItem<*>) =
        (newItem as? HeaderAdapterItem)?.userName == userName
}

class HeaderViewHolder(view: View) : BaseViewHolder(view) {
    val nameTextView: TextView = bindView(R.id.name_text_view)
}