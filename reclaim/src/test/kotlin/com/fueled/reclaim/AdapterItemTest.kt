package com.fueled.reclaim

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * Created by hussein@fueled.com on 01/09/2018.
 */
class AdapterItemTest {

    @Test
    fun testOnBindAndGetViewHolder() {
        // Given
        val viewHolder: TestAdapterItem.ViewHolder = mock {
            on { adapterPosition } doReturn 0
        }

        val item = spy(TestAdapterItem())
        item.positionInAdapter = 0

        // When
        item.onBindViewHolder(viewHolder)

        // Then
        verify(item).updateItemViews(viewHolder)
    }

    @Test
    fun testOnBindAndGetViewHolderWithEmptyPayloads() {
        // Given
        val viewHolder: TestAdapterItem.ViewHolder = mock {
            on { adapterPosition } doReturn 0
        }
        val payloads = emptyList<Any>()

        val item = spy(TestAdapterItem())
        item.positionInAdapter = 0

        // When
        item.onBindViewHolder(viewHolder, payloads)

        // Then
        verify(item).updateItemViews(viewHolder)
    }

    @Test
    fun testOnBindAndGetViewHolderWithPayloads() {
        // Given
        val viewHolder: TestAdapterItem.ViewHolder = mock {
            on { adapterPosition } doReturn 0
        }
        val payloads = listOf<Any>("Test")

        val item = spy(TestAdapterItem())
        item.positionInAdapter = 0

        // When
        item.onBindViewHolder(viewHolder, payloads)

        // Then
        verify(item).updateItemViews(viewHolder, payloads)
    }

    @Test
    fun testIsTheSameReturnsFalseByDefault() {
        // Given
        val itemOne = TestAdapterItem()
        val itemTwo = TestAdapterItem()

        // When
        val isTheSame = itemOne.isTheSame(itemTwo)

        // Then
        assertFalse(isTheSame)
    }

    @Test
    fun testIsContentsTheSameReturnsFalseByDefault() {
        // Given
        val itemOne = TestAdapterItem()
        val itemTwo = TestAdapterItem()

        // When
        val isTheSame = itemOne.isContentsTheSame(itemTwo)

        // Then
        assertFalse(isTheSame)
    }

    @Test
    fun testGetChangePayloadReturnsNullByDefault() {
        // Given
        val itemOne = TestAdapterItem()
        val itemTwo = TestAdapterItem()

        // When
        val changePayload = itemOne.getChangePayload(itemTwo)

        // Then
        assertNull(changePayload)
    }
}