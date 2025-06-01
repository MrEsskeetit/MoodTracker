package com.moodtracker.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

class FakeMoodRepository {
    private val moodList = mutableListOf<MoodEntry>()
    private val moodsLiveData = MutableLiveData<List<MoodEntry>>(moodList)

    fun addMood(moodEntry: MoodEntry) {
        moodList.add(0, moodEntry)
        moodsLiveData.value = moodList.toList()
    }

    fun getAllMoods(): LiveData<List<MoodEntry>> = moodsLiveData

    fun getMoodById(id: UUID): MoodEntry? = moodList.find { it.id == id }
}
