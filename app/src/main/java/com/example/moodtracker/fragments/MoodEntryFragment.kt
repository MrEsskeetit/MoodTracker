package com.example.moodtracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.moodtracker.R
import com.example.moodtracker.adapter.MoodEntry
import com.example.moodtracker.data.FakeMoodRepository
import java.util.*

class MoodEntryFragment : Fragment() {

    private lateinit var noteEditText: EditText
    private lateinit var moodRadioGroup: RadioGroup
    private lateinit var categorySpinner: Spinner
    private lateinit var sleptCheckBox: CheckBox
    private lateinit var activeCheckBox: CheckBox
    private lateinit var ratingBar: RatingBar
    private lateinit var importantSwitch: Switch
    private lateinit var saveButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mood_entry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Inicjalizacja widoków
        noteEditText = view.findViewById(R.id.noteEditText)
        moodRadioGroup = view.findViewById(R.id.moodRadioGroup)
        categorySpinner = view.findViewById(R.id.categorySpinner)
        sleptCheckBox = view.findViewById(R.id.sleptCheckBox)
        activeCheckBox = view.findViewById(R.id.activeCheckBox)
        ratingBar = view.findViewById(R.id.ratingBar)
        importantSwitch = view.findViewById(R.id.importantSwitch)
        saveButton = view.findViewById(R.id.saveButton)

        saveButton.setOnClickListener {
            saveMoodEntry()
        }
    }

    private fun saveMoodEntry() {
        val note = noteEditText.text.toString()
        val selectedMoodId = moodRadioGroup.checkedRadioButtonId
        val mood = view?.findViewById<RadioButton>(selectedMoodId)?.text.toString()
        val category = categorySpinner.selectedItem.toString()
        val sleptWell = sleptCheckBox.isChecked
        val wasActive = activeCheckBox.isChecked
        val rating = ratingBar.rating.toInt()
        val isImportant = importantSwitch.isChecked

        val newEntry = MoodEntry(
            note = note,
            mood = mood,
            category = category,
            sleptWell = sleptWell,
            wasActive = wasActive,
            rating = rating,
            isImportant = isImportant
        )

        FakeMoodRepository.addMood(newEntry)
        Toast.makeText(requireContext(), "Wpis zapisany!", Toast.LENGTH_SHORT).show()

        // Wyczyść formularz
        noteEditText.setText("")
        moodRadioGroup.clearCheck()
        categorySpinner.setSelection(0)
        sleptCheckBox.isChecked = false
        activeCheckBox.isChecked = false
        ratingBar.rating = 0f
        importantSwitch.isChecked = false
    }
}
