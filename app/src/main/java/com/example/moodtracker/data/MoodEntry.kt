package com.moodtracker.data

import java.util.UUID
import java.util.Date

data class MoodEntry(
    val id: UUID = UUID.randomUUID(),
    val date: Date = Date(),
    val note: String,
    val mood: Mood,
    val category: String,
    val sleptWell: Boolean,
    val physicalActivity: Boolean,
    val rating: Int,
    val important: Boolean,
    val userSignature: String? = null
)

enum class Mood {
    HAPPY, NEUTRAL, SAD
}
