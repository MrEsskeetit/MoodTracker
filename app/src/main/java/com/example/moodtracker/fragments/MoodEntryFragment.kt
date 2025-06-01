package com.moodtracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.moodtracker.R
import com.moodtracker.data.FakeMoodRepository
import com.moodtracker.data.Mood
import com.moodtracker.data.MoodEntry

class MoodEntryFragment : Fragment() {

    private lateinit var noteEditText: EditText
    private lateinit var moodRadioGroup: RadioGroup
    private lateinit var categorySpinner: Spinner
    private lateinit var sleptWellCheckBox: CheckBox
    private lateinit var physicalActivityCheckBox: CheckBox
    private lateinit var ratingBar: RatingBar
    private lateinit var importantSwitch: Switch
    private lateinit var saveButton: Button

    private val categories = listOf("Szkoła", "Dom", "Znajomi", "Zdrowie", "Inne")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mood_entry, container, false)

        noteEditText = view.findViewById(R.id.et_note)
        moodRadioGroup = view.findViewById(R.id.rg_mood)
        categorySpinner = view.findViewById(R.id.spinner_category)
        sleptWellCheckBox = view.findViewById(R.id.cb_slept_well)
        physicalActivityCheckBox = view.findViewById(R.id.cb_physical_activity)
        ratingBar = view.findViewById(R.id.rating_day)
        importantSwitch = view.findViewById(R.id.switch_important)
        saveButton = view.findViewById(R.id.btn_save)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = adapter

        saveButton.setOnClickListener {
            saveMoodEntry()
        }

        return view
    }

    private fun saveMoodEntry() {
        val note = noteEditText.text.toString()
        val mood = when(moodRadioGroup.checkedRadioButtonId) {
            R.id.rb_happy -> Mood.HAPPY
            R.id.rb_neutral -> Mood.NEUTRAL
            R.id.rb_sad -> Mood.SAD
            else -> Mood.NEUTRAL
        }
        val category = categorySpinner.selectedItem as String
        val sleptWell = sleptWellCheckBox.isChecked
        val physicalActivity = physicalActivityCheckBox.isChecked
        val rating = ratingBar.rating.toInt()
        val important = importantSwitch.isChecked

        val newEntry = MoodEntry(
            note = note,
            mood = mood,
            category = category,
            sleptWell = sleptWell,
            physicalActivity = physicalActivity,
            rating = rating,
            important = important
        )

        FakeMoodRepository.addMood(newEntry)

        Toast.makeText(requireContext(), "Wpis zapisany!", Toast.LENGTH_SHORT).show()

        findNavController().navigate(R.id.action_moodEntryFragment_to_moodHistoryFragment)
    }
}
