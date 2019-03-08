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

import androidx.recyclerview.widget.DiffUtil

class DiffChecker(
    private val oldItemsList: List<AdapterItem<*>>,
    private val newItemsList: List<AdapterItem<*>>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItemsList.size

    override fun getNewListSize(): Int = newItemsList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return getOldItem(oldItemPosition).isTheSame(getNewItem(newItemPosition))
    }

    @Suppress("UNCHECKED_CAST")
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = getOldItem(oldItemPosition)
        val newItem = getNewItem(newItemPosition)
        return oldItem.isContentsTheSame(newItem)
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return getOldItem(oldItemPosition).getChangePayload(getNewItem(newItemPosition))
    }

    private fun getOldItem(position: Int): AdapterItem<*> = oldItemsList[position]

    private fun getNewItem(position: Int): AdapterItem<*> = newItemsList[position]

    fun calculateDiff(): DiffUtil.DiffResult {
        return DiffUtil.calculateDiff(this)
    }
}
