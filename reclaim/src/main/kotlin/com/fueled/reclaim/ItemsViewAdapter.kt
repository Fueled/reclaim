/*
 * Copyright (C) 2018 Fueled.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fueled.reclaim

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import java.util.ArrayList

@Suppress("unused", "MemberVisibilityCanBePrivate") // Public API.
open class ItemsViewAdapter @JvmOverloads constructor(
    private val items: MutableList<AdapterItem<*>> = ArrayList()
) : RecyclerView.Adapter<BaseViewHolder>(), Iterable<AdapterItem<*>> {

    /**
     * Returns an iterator over the elements in this adapter in proper sequence.
     *
     * @return an iterator over the elements in this adapter in proper sequence
     */
    override fun iterator() = items.iterator()

    /**
     * Add multiple items to the adapter.
     *
     * @param items the list of items to be added
     */
    fun addItemsList(items: List<AdapterItem<*>>) {
        val startSize = itemCount

        this.items.addAll(items)
        updateItemPositions(startSize)
        notifyItemRangeInserted(startSize, items.size)
    }

    /**
     * Add multiple items to the adapter at the specified position
     * and shifts items that are currently at this position to the right.
     *
     * @param position the position at which to add the first item in the list
     * @param items the list of item to be added
     */
    fun addItemsList(position: Int, items: List<AdapterItem<*>>) {
        this.items.addAll(position, items)
        updateItemPositions(position)
        notifyItemRangeInserted(position, items.size)
    }

    /**
     * Removes all items inside the adapter and add new items from the specified list.
     *
     * @param items the list of item to be added
     * @param useDiffCheck indicates whether [DiffChecker] should be used to only apply needed
     * changes.
     */
    @JvmOverloads
    fun replaceItems(items: List<AdapterItem<*>>, useDiffCheck: Boolean = false) {
        if (!useDiffCheck) {
            this.items.clear()
            this.items.addAll(items)
            updateItemPositions(0)
            notifyDataSetChanged()
        } else {
            val result = getDiffChecker(
                oldItemsList = this.items.toList(), newItemsList = items
            ).calculateDiff()
            replaceItems(items, result)
        }
    }

    /**
     * Use the provided [diffResult] to preform the required updates to match the state of the
     * specified list of [items].
     *
     * @param items the list of items to be added.
     * @param diffResult the required updates to be preformed.
     */
    fun replaceItems(items: List<AdapterItem<*>>, diffResult: DiffUtil.DiffResult) {
        this.items.clear()
        this.items.addAll(items)
        updateItemPositions(0)
        diffResult.dispatchUpdatesTo(this)
    }

    fun replaceItemAt(position: Int, item: AdapterItem<*>) {
        items[position] = item
        item.positionInAdapter = position
        notifyItemChanged(position)
    }

    /**
     * Add item to the adapter.
     *
     * @param item the item to be added
     */
    fun addItem(item: AdapterItem<*>) {
        addItem(items.size, item)
    }

    /**
     * Add item to the adapter at the specified position.
     *
     * @param position the position at which to add the item in the list
     * @param item the item to be added
     */
    fun addItem(position: Int, item: AdapterItem<*>) {
        items.add(position, item)
        updateItemPositions(position)
        notifyItemInserted(position)
    }

    /**
     * Removes the item at the specified position in this adapter view.
     *
     * @param position the position of the item to be removed
     */
    fun removeItemAt(position: Int) {
        items.removeAt(position)
        updateItemPositions(position)
        notifyItemRemoved(position)
    }

    /**
     * Removes all items inside the recycler adapter view.
     */
    fun clearAllRecyclerItems() {
        val total = items.size

        items.clear()

        notifyItemRangeRemoved(0, total)
    }

    private fun updateItemPositions(startPosition: Int) {
        for (i in startPosition until items.size) {
            items[i].positionInAdapter = i
        }
    }

    /**
     * Returns the item at the specified position in the adapter.
     *
     * @param position position of the item to be returned
     * @return the item at the specified position in the adapter
     */
    fun getItem(position: Int): AdapterItem<*> = items[position]

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseViewHolder {
        for (item in items) {
            if (item.layoutId == viewType) {
                val view = getLayoutInflater(viewGroup.context)
                    .inflate(item.layoutId, viewGroup, false)

                return item.onCreateViewHolder(view)
            }
        }

        throw Exception("Invalid view type: $viewType")
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        (items[position] as AdapterItem<BaseViewHolder>).onBindViewHolder(holder)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int, payloads: List<Any>) {
        (items[position] as AdapterItem<BaseViewHolder>).onBindViewHolder(holder, payloads)
    }

    override fun getItemViewType(position: Int) = items[position].layoutId

    override fun getItemCount() = items.size

    @VisibleForTesting
    internal fun getDiffChecker(
        oldItemsList: List<AdapterItem<*>>,
        newItemsList: List<AdapterItem<*>>
    ) = DiffChecker(oldItemsList, newItemsList)

    @VisibleForTesting
    internal fun getLayoutInflater(context: Context) = LayoutInflater.from(context)
}