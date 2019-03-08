package com.fueled.reclaim

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

/**
 * Created by hussein@fueled.com on 01/09/2018.
 */
class DiffCheckerTest {

    @Test
    fun testGetOldListSize() {
        // Given
        val oldList = TestsMockFactory.getTestAdapterItems(5)
        val newList = TestsMockFactory.getTestAdapterItems(3)
        val diffChecker = DiffChecker(oldItemsList = oldList, newItemsList = newList)

        // When
        val count = diffChecker.oldListSize

        // Then
        assertEquals(5, count)
    }

    @Test
    fun testGetNewListSize() {
        // Given
        val oldList = TestsMockFactory.getTestAdapterItems(5)
        val newList = TestsMockFactory.getTestAdapterItems(3)
        val diffChecker = DiffChecker(oldItemsList = oldList, newItemsList = newList)

        // When
        val count = diffChecker.newListSize

        // Then
        assertEquals(3, count)
    }

    @Test
    fun testAreItemsTheSame() {
        // Given
        val oldItem: TestAdapterItem = mock()
        val newItem: TestAdapterItem = mock()
        val diffChecker = DiffChecker(oldItemsList = listOf(oldItem), newItemsList = listOf(newItem))

        whenever(oldItem.isTheSame(newItem)).thenReturn(false)

        // When
        val areTheSame = diffChecker.areItemsTheSame(0, 0)

        // Then
        verify(oldItem).isTheSame(newItem)

        assertEquals(false, areTheSame)
    }

    @Test
    fun testAreContentsTheSame() {
        // Given
        val oldItem: TestAdapterItem = mock()
        val newItem: TestAdapterItem = mock()
        val diffChecker = DiffChecker(oldItemsList = listOf(oldItem), newItemsList = listOf(newItem))

        whenever(oldItem.isContentsTheSame(newItem)).thenReturn(false)

        // When
        val areTheSame = diffChecker.areContentsTheSame(0, 0)

        // Then
        verify(oldItem).isContentsTheSame(newItem)

        assertEquals(false, areTheSame)
    }

    @Test
    fun testGetChangePayload() {
        // Given
        val oldItem: TestAdapterItem = mock()
        val newItem: TestAdapterItem = mock()
        val diffChecker = DiffChecker(oldItemsList = listOf(oldItem), newItemsList = listOf(newItem))

        val newTitle = "test title"

        whenever(oldItem.getChangePayload(newItem)).thenReturn(newTitle)

        // When
        val changePayload = diffChecker.getChangePayload(0, 0)

        // Then
        verify(oldItem).getChangePayload(newItem)

        assertEquals("test title", changePayload)
    }

    @Test
    fun testCalculateDiff() {
        // Given
        val diffChecker = DiffChecker(emptyList(), emptyList())

        // When
        val result = diffChecker.calculateDiff()

        // Then
        assertNotNull(result)
    }
}