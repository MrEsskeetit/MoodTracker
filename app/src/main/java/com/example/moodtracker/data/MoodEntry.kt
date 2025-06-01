package com.example.moodtracker.data

import java.util.UUID

data class MoodEntry(
    val id: UUID = UUID.randomUUID(),
    val note: String,
    val mood: String,
    val category: String,
    val sleptWell: Boolean,
    val wasActive: Boolean,
    val rating: Int,
    val isImportant: Boolean
)