package com.fueled.reclaim

import android.view.View

/**
 * Created by hussein@fueled.com on 01/09/2018.
 */
object TestsMockFactory {

    const val TEST_1_ADAPTER_ID = 0x1
    const val TEST_2_ADAPTER_ID = 0x2
    const val TEST_INVALID_ADAPTER_ID = 0x0

    fun getTestAdapterItems(numberOfItems: Int): List<TestAdapterItem> = (0 until numberOfItems)
            .map { TestAdapterItem() }
}

class TestAdapterItem : AdapterItem<TestAdapterItem.ViewHolder>() {

    override val layoutId: Int
        get() = TestsMockFactory.TEST_1_ADAPTER_ID

    override fun onCreateViewHolder(view: View) = ViewHolder(view)

    override fun updateItemViews(viewHolder: ViewHolder) {
    }

    inner class ViewHolder(view: View) : BaseViewHolder(view)
}