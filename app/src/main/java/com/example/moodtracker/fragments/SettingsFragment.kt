package com.moodtracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.moodtracker.R

class SettingsFragment : Fragment() {

    private lateinit var darkModeSwitch: Switch
    private lateinit var defaultMoodSpinner: Spinner
    private lateinit var userSignatureEditText: EditText

    private val moods = listOf("Wesoły", "Przeciętny", "Smutny")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        darkModeSwitch = view.findViewById(R.id.switch_dark_mode)
        defaultMoodSpinner = view.findViewById(R.id.spinner_default_mood)
        userSignatureEditText = view.findViewById(R.id.et_user_signature)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, moods)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        defaultMoodSpinner.adapter = adapter

        return view
    }
}
