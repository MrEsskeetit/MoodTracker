package com.moodtracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.moodtracker.R
import com.moodtracker.data.FakeMoodRepository

class MoodDetailsFragment : Fragment() {

    private val args: MoodDetailsFragmentArgs by navArgs()

    private lateinit var dateText: TextView
    private lateinit var moodText: TextView
    private lateinit var categoryText: TextView
    private lateinit var noteText: TextView
    private lateinit var sleptWellText: TextView
    private lateinit var physicalActivityText: TextView
    private lateinit var ratingText: TextView
    private lateinit var deleteButton: Button
    private lateinit var shareButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mood_details, container, false)

        dateText = view.findViewById(R.id.tv_date)
        moodText = view.findViewById(R.id.tv_mood)
        categoryText = view.findViewById(R.id.tv_category)
        noteText = view.findViewById(R.id.tv_note)
        sleptWellText = view.findViewById(R.id.tv_slept_well)
        physicalActivityText = view.findViewById(R.id.tv_physical_activity)
        ratingText = view.findViewById(R.id.tv_rating)
        deleteButton = view.findViewById(R.id.btn_delete)
        shareButton = view.findViewById(R.id.btn_share)

        val moodEntry = FakeMoodRepository.getMoodById(java.util.UUID.fromString(args.moodId))

        if (moodEntry != null) {
            dateText.text = moodEntry.date.toString()
            moodText.text = moodEntry.mood.name
            categoryText.text = moodEntry.category
            noteText.text = moodEntry.note
            sleptWellText.text = "Spałem dobrze: ${if (moodEntry.sleptWell) "Tak" else "Nie"}"
            physicalActivityText.text = "Aktywny fizycznie: ${if (moodEntry.physicalActivity) "Tak" else "Nie"}"
            ratingText.text = "Ocena dnia: ${moodEntry.rating}"
        }

        deleteButton.setOnClickListener {
            if (moodEntry != null) {
                FakeMoodRepository.deleteMood(moodEntry.id)
                Toast.makeText(requireContext(), "Wpis usunięty", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
        }

        shareButton.setOnClickListener {
            Toast.makeText(requireContext(), "Funkcja udostępniania jeszcze nie zaimplementowana", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
