package com.moodtracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.moodtracker.R
import com.moodtracker.data.FakeMoodRepository
import com.moodtracker.data.Mood
import com.moodtracker.data.MoodEntry

class MoodEntryFragment : Fragment() {

    private val repository = FakeMoodRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mood_entry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val spinner: Spinner = view.findViewById(R.id.mood_spinner)
        val noteEditText: EditText = view.findViewById(R.id.note_edit_text)
        val saveButton: Button = view.findViewById(R.id.save_button)

        spinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            Mood.values().map { it.name }
        )

        saveButton.setOnClickListener {
            val selectedMood = Mood.valueOf(spinner.selectedItem as String)
            val note = noteEditText.text.toString().takeIf { it.isNotBlank() }

            val moodEntry = MoodEntry(
                mood = selectedMood,
                note = note
            )

            repository.addMood(moodEntry)

            findNavController().navigateUp()
        }
    }
}
