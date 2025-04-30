

package com.example.moodtracker.data

import java.util.UUID

data class MoodEntry(
    val id: UUID = UUID.randomUUID(),
    val date: Long = System.currentTimeMillis(),
    val note: String,
    val mood: MoodType,
    val category: String,
    val sleptWell: Boolean,
    val wasActive: Boolean,
    val dayRating: Float,
    val isImportant: Boolean,
    val userSignature: String = ""
)

enum class MoodType {
    HAPPY, AVERAGE, SAD;

    companion object {
        fun fromPosition(position: Int): MoodType {
            return when(position) {
                0 -> HAPPY
                2 -> SAD
                else -> AVERAGE
            }
        }
    }
}