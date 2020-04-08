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

import android.view.View

abstract class AdapterItem<T2 : BaseViewHolder> {

    /**
     * Interface that define the layout of each item
     *
     * @return the layout id to be used by the item
     */
    abstract val layoutId: Int

    /**
     * Get the position of the item in the Adapter.
     *
     * @return The position in the adapter.
     */
    var positionInAdapter: Int = 0

    /**
     * Called when [ItemsViewAdapter] needs a new [ViewHolder][android.support.v7.widget.RecyclerView.ViewHolder]
     * to represent this item
     *
     * @param view the view to bind to the new view holder
     * @return a new view holder that holds the view
     */
    abstract fun onCreateViewHolder(view: View): T2

    /**
     * This method should update the contents of the [ViewHolder#itemView][android.support.v7.widget.RecyclerView.ViewHolder.itemView]
     * to reflect the data of this item.
     */
    open fun updateItemViews(viewHolder: T2) {}

    /**
     * This method should update the contents of the [ViewHolder#itemView][android.support.v7.widget.RecyclerView.ViewHolder.itemView]
     * to reflect the data of this item.
     */
    open fun updateItemViews(viewHolder: T2, payloads: List<Any>) {
        updateItemViews(viewHolder)
    }

    /**
     * Called by the [ItemsViewAdapter] to bind the provided
     * [ViewHolder][android.support.v7.widget.RecyclerView.ViewHolder] to this item. This method will also
     * invoke [updateItemViews].
     *
     * @param viewHolder the view holder to bind to this item
     */
    fun onBindViewHolder(viewHolder: T2) {
        updateItemViews(viewHolder)
    }

    /**
     * Called by the [ItemsViewAdapter] to bind the provided
     * [ViewHolder][android.support.v7.widget.RecyclerView.ViewHolder] to this item. This method will also
     * invoke [updateItemViews].
     *
     * @param viewHolder the view holder to bind to this item
     */
    fun onBindViewHolder(viewHolder: T2, payloads: List<Any>) {
        if (payloads.isEmpty()) {
            updateItemViews(viewHolder)
        } else {
            updateItemViews(viewHolder, payloads)
        }
    }

    /**
     * Called by the DiffChecker to decide whether two object represent the same Item.
     *
     *
     * For example, if your items have unique ids, this method should check their id equality.
     *
     * @param newItem the other item to be checked.
     * @return True if the two items represent the same object or false if they are different.
     */
    open fun isTheSame(newItem: AdapterItem<*>): Boolean {
        return false
    }

    /**
     * Called by the DiffChecker when it wants to check whether two items have the same data.
     * DiffChecker uses this information to detect if the contents of an item has changed.
     *
     *
     * DiffChecker uses this method to check equality instead of [Object.equals]
     * so that you can change its behavior depending on your UI.
     *
     *
     * You should return whether the items' visual representations are the same.
     *
     *
     * This method is called only if [isTheSame] returns
     * `true` for these items.
     *
     * @param newItem the other item to be checked.
     * @return True if the contents of the items are the same or false if they are different.
     */
    open fun isContentsTheSame(newItem: AdapterItem<*>): Boolean {
        return isTheSame(newItem)
    }

    /**
     * When [isTheSame] returns `true` for two items and
     * [isContentsTheSame] returns `false` for them, DiffUtil
     * calls this method to get a payload about the change.
     *
     * For example, you can return the particular field that changed in the item and your
     * [ItemAnimator][android.support.v7.widget.RecyclerView.ItemAnimator] can use that
     * information to run the correct animation.
     *
     * Default implementation returns `null`.
     *
     * @param newItem The new item with the changed content.
     *
     * @return A payload object that represents the change between the two items.
     */
    open fun getChangePayload(newItem: AdapterItem<*>): Any? {
        return null
    }
}
