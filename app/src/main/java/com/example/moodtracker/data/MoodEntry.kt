package com.moodtracker.data

import java.util.*

data class MoodEntry(
    val id: UUID = UUID.randomUUID(),
    val mood: Mood,
    val note: String?,
    val date: Date = Date()
)

enum class Mood {
    HAPPY, SAD, ANGRY, NEUTRAL, EXCITED
}
