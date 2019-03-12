package com.fueled.reclaim.samples.notes

import java.util.UUID

/**
 * Created by hussein@fueled.com on 05/02/2019.
 */
data class Note(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val body: String,
    val timestamp: Long = System.currentTimeMillis()
)