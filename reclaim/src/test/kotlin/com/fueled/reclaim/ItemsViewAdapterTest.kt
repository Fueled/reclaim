package com.fueled.reclaim

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doCallRealMethod
import com.nhaarman.mockito_kotlin.doNothing
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

/**
 * Created by hussein@fueled.com on 31/08/2018.
 */
class ItemsViewAdapterTest {

    private val context: Context = mock()
    private val diffResult: DiffUtil.DiffResult = mock()
    private val diffChecker: DiffChecker = mock {
        on { calculateDiff() } doReturn diffResult
    }

    private lateinit var itemsAdapter: ItemsViewAdapter

    @Before
    fun setup() {
        itemsAdapter = spy(ItemsViewAdapter(context))

        doReturn(diffChecker).whenever(itemsAdapter).getDiffChecker(any(), any())
    }

    @Test
    fun testAddItemsListAdapterEmpty() {
        // Given
        doNothing().whenever(itemsAdapter).notifyItemRangeInserted(any(), any())
        val items = TestsMockFactory.getTestAdapterItems(5)

        // When
        itemsAdapter.addItemsList(items)

        // Then
        verify(itemsAdapter).notifyItemRangeInserted(0, 5)

        itemsAdapter.forEachIndexed { index, item -> assertEquals(index, item.positionInAdapter) }
    }

    @Test
    fun testAddItemsListAdapterNotEmpty() {
        // Given
        doNothing().whenever(itemsAdapter).notifyItemRangeInserted(any(), any())
        val firstList = TestsMockFactory.getTestAdapterItems(5)
        val secondList = TestsMockFactory.getTestAdapterItems(3)

        // When
        itemsAdapter.addItemsList(firstList)
        itemsAdapter.addItemsList(secondList)

        // Then
        verify(itemsAdapter).notifyItemRangeInserted(0, 5)
        verify(itemsAdapter).notifyItemRangeInserted(5, 3)

        itemsAdapter.forEachIndexed { index, item -> assertEquals(index, item.positionInAdapter) }
    }

    @Test
    fun testAddItemsListAtPosition() {
        // Given
        doNothing().whenever(itemsAdapter).notifyItemRangeInserted(any(), any())
        val firstList = TestsMockFactory.getTestAdapterItems(5)
        val secondList = TestsMockFactory.getTestAdapterItems(3)

        // When
        itemsAdapter.addItemsList(firstList)
        itemsAdapter.addItemsList(2, secondList)

        // Then
        verify(itemsAdapter).notifyItemRangeInserted(0, 5)
        verify(itemsAdapter).notifyItemRangeInserted(2, 3)

        itemsAdapter.forEachIndexed { index, item -> assertEquals(index, item.positionInAdapter) }
    }

    @Test
    fun testReplaceItems() {
        // Given
        doNothing().whenever(itemsAdapter).notifyItemRangeInserted(any(), any())
        doNothing().whenever(itemsAdapter).notifyDataSetChanged()

        val originalList = TestsMockFactory.getTestAdapterItems(5)
        val newList = TestsMockFactory.getTestAdapterItems(3)

        // When
        itemsAdapter.addItemsList(originalList)
        itemsAdapter.replaceItems(newList)

        // Then
        verify(itemsAdapter).notifyDataSetChanged()
        verify(itemsAdapter, never()).getDiffChecker(any(), any())

        assertEquals(3, itemsAdapter.count())

        itemsAdapter.forEachIndexed { index, item ->
            assertEquals(index, item.positionInAdapter)
            assertEquals(newList[index], item)
        }
    }

    @Test
    fun testReplaceItemsUseDiffCheck() {
        // Given
        doNothing().whenever(itemsAdapter).notifyItemRangeInserted(any(), any())
        doNothing().whenever(itemsAdapter).notifyDataSetChanged()

        val originalList = TestsMockFactory.getTestAdapterItems(5)
        val newList = TestsMockFactory.getTestAdapterItems(3)

        // When
        itemsAdapter.addItemsList(originalList)
        itemsAdapter.replaceItems(newList, true)

        // Then
        verify(itemsAdapter).getDiffChecker(any(), any())
        verify(diffChecker).calculateDiff()
        verify(diffResult).dispatchUpdatesTo(itemsAdapter)

        assertEquals(3, itemsAdapter.itemCount)

        itemsAdapter.forEachIndexed { index, item ->
            assertEquals(index, item.positionInAdapter)
            assertEquals(newList[index], item)
        }
    }

    @Test
    fun testReplaceItemAt() {
        // Given
        doNothing().whenever(itemsAdapter).notifyItemRangeInserted(any(), any())
        doNothing().whenever(itemsAdapter).notifyItemChanged(3)

        val originalList = TestsMockFactory.getTestAdapterItems(5)
        val newItem = TestAdapterItem()

        // When
        itemsAdapter.addItemsList(originalList)
        itemsAdapter.replaceItemAt(3, newItem)

        // Then
        verify(itemsAdapter).notifyItemChanged(3)

        assertEquals(newItem, itemsAdapter.getItem(3))
        assertEquals(5, itemsAdapter.itemCount)

        itemsAdapter.forEachIndexed { index, item ->
            assertEquals(index, item.positionInAdapter)
        }
    }

    @Test
    fun testAddItem() {
        // Given
        doNothing().whenever(itemsAdapter).notifyItemInserted(0)

        val newItem = TestAdapterItem()

        // When
        itemsAdapter.addItem(newItem)

        // Then
        verify(itemsAdapter).notifyItemInserted(0)
        assertEquals(1, itemsAdapter.itemCount)
        assertEquals(newItem, itemsAdapter.getItem(0))
    }

    @Test
    fun testAddItemAtPosition() {
        // Given
        doNothing().whenever(itemsAdapter).notifyItemRangeInserted(any(), any())
        doNothing().whenever(itemsAdapter).notifyItemInserted(3)

        val originalList = TestsMockFactory.getTestAdapterItems(5)
        val newItem = TestAdapterItem()

        // When
        itemsAdapter.addItemsList(originalList)
        itemsAdapter.addItem(3, newItem)

        // Then
        verify(itemsAdapter).notifyItemInserted(3)

        assertEquals(newItem, itemsAdapter.getItem(3))
        assertEquals(6, itemsAdapter.itemCount)

        itemsAdapter.forEachIndexed { index, item ->
            assertEquals(index, item.positionInAdapter)
        }
    }

    @Test
    fun testRemoveItemAt() {
        // Given
        doNothing().whenever(itemsAdapter).notifyItemRangeInserted(any(), any())
        doNothing().whenever(itemsAdapter).notifyItemRemoved(3)

        val originalList = TestsMockFactory.getTestAdapterItems(5)
        val itemToBeRemoved = originalList[3]

        // When
        itemsAdapter.addItemsList(originalList)
        itemsAdapter.removeItemAt(3)

        // Then
        verify(itemsAdapter).notifyItemRemoved(3)

        assertNotEquals(itemToBeRemoved, itemsAdapter.getItem(3))
        assertEquals(4, itemsAdapter.itemCount)

        itemsAdapter.forEachIndexed { index, item ->
            assertEquals(index, item.positionInAdapter)
        }
    }

    @Test
    fun testClearAllRecyclerItems() {
        // Given
        doNothing().whenever(itemsAdapter).notifyItemRangeInserted(any(), any())
        doNothing().whenever(itemsAdapter).notifyItemRangeRemoved(0, 5)

        val items = TestsMockFactory.getTestAdapterItems(5)

        // When
        itemsAdapter.addItemsList(items)
        itemsAdapter.clearAllRecyclerItems()

        // Then
        verify(itemsAdapter).notifyItemRangeRemoved(0, 5)

        assertEquals(0, itemsAdapter.itemCount)
    }

    @Test
    fun testOnCreateViewHolder() {
        // Given
        doNothing().whenever(itemsAdapter).notifyItemRangeInserted(any(), any())

        val viewGroup: ViewGroup = mock()
        val view: View = mock()
        val layoutInflater: LayoutInflater = mock {
            on { inflate(TestsMockFactory.TEST_2_ADAPTER_ID, viewGroup, false) } doReturn view
        }

        doReturn(layoutInflater).whenever(itemsAdapter).getLayoutInflater()

        val viewHolder: BaseViewHolder = mock()
        val viewHolder2: BaseViewHolder = mock()

        val type1AdapterItem = mock<AdapterItem<BaseViewHolder>> {
            on { layoutId } doReturn TestsMockFactory.TEST_1_ADAPTER_ID
            on { onCreateViewHolder(view) } doReturn viewHolder
        }

        val type2AdapterItem = mock<AdapterItem<BaseViewHolder>> {
            on { layoutId } doReturn TestsMockFactory.TEST_2_ADAPTER_ID
            on { onCreateViewHolder(view) } doReturn viewHolder2
        }

        val items = listOf(type1AdapterItem, type2AdapterItem)

        // When
        itemsAdapter.addItemsList(items)

        val vh = itemsAdapter.onCreateViewHolder(viewGroup, TestsMockFactory.TEST_2_ADAPTER_ID)

        // Then
        verify(itemsAdapter).getLayoutInflater()
        verify(layoutInflater).inflate(TestsMockFactory.TEST_2_ADAPTER_ID, viewGroup, false)
        verify(type2AdapterItem).onCreateViewHolder(view)
        verify(type1AdapterItem, never()).onCreateViewHolder(view)

        assertEquals(viewHolder2, vh)
    }

    @Test(expected = Exception::class)
    fun testOnCreateViewHolderInvalidId() {
        // Given
        doNothing().whenever(itemsAdapter).notifyItemRangeInserted(any(), any())

        val viewGroup: ViewGroup = mock()

        val type1AdapterItem = mock<AdapterItem<BaseViewHolder>> {
            on { layoutId } doReturn TestsMockFactory.TEST_1_ADAPTER_ID
        }

        val type2AdapterItem = mock<AdapterItem<BaseViewHolder>> {
            on { layoutId } doReturn TestsMockFactory.TEST_2_ADAPTER_ID
        }

        val items = listOf(type1AdapterItem, type2AdapterItem)

        // When
        itemsAdapter.addItemsList(items)
        itemsAdapter.onCreateViewHolder(viewGroup, TestsMockFactory.TEST_INVALID_ADAPTER_ID)
    }

    @Test
    fun testOnBindViewHolder() {
        // Given
        doNothing().whenever(itemsAdapter).notifyItemInserted(0)
        val viewHolder: TestAdapterItem.ViewHolder = mock()
        val item = mock<TestAdapterItem>()

        // When
        itemsAdapter.addItem(item)
        itemsAdapter.onBindViewHolder(viewHolder, 0)

        // Then
        verify(item).onBindViewHolder(viewHolder)
    }

    @Test
    fun testOnBindViewHolderWithPayload() {
        // Given
        doNothing().whenever(itemsAdapter).notifyItemInserted(0)
        val viewHolder: TestAdapterItem.ViewHolder = mock()
        val item = mock<TestAdapterItem>()
        val payloads = emptyList<Any>()

        // When
        itemsAdapter.addItem(item)
        itemsAdapter.onBindViewHolder(viewHolder, 0, payloads)

        // Then
        verify(item).onBindViewHolder(viewHolder, payloads)
    }

    @Test
    fun testGetItemViewType() {
        // Given
        doNothing().whenever(itemsAdapter).notifyItemRangeInserted(any(), any())

        val type1AdapterItem = mock<AdapterItem<BaseViewHolder>> {
            on { layoutId } doReturn TestsMockFactory.TEST_1_ADAPTER_ID
        }

        val type2AdapterItem = mock<AdapterItem<BaseViewHolder>> {
            on { layoutId } doReturn TestsMockFactory.TEST_2_ADAPTER_ID
        }

        val items = listOf(type1AdapterItem, type2AdapterItem)

        // When
        itemsAdapter.addItemsList(items)
        val typeForPosition0 = itemsAdapter.getItemViewType(0)
        val typeForPosition1 = itemsAdapter.getItemViewType(1)

        // Then
        assertEquals(TestsMockFactory.TEST_1_ADAPTER_ID, typeForPosition0)
        assertEquals(TestsMockFactory.TEST_2_ADAPTER_ID, typeForPosition1)
    }

    @Test
    fun testGetDiffChecker() {
        // Given
        doCallRealMethod().whenever(itemsAdapter).getDiffChecker(any(), any())

        // When
        val diffChecker = itemsAdapter.getDiffChecker(emptyList(), emptyList())

        // Then
        assertNotNull(diffChecker)
    }
}