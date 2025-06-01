package com.moodtracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.moodtracker.R
import com.moodtracker.data.FakeMoodRepository

class MoodDetailsFragment : Fragment() {

    private val args: MoodDetailsFragmentArgs by navArgs()
    private val repository = FakeMoodRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mood_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val moodId = args.moodId
        val moodEntry = repository.getMoodById(java.util.UUID.fromString(moodId))

        val moodTextView: TextView = view.findViewById(R.id.mood_text)
        val noteTextView: TextView = view.findViewById(R.id.note_text)
        val dateTextView: TextView = view.findViewById(R.id.date_text)

        moodEntry?.let {
            moodTextView.text = it.mood.name
            noteTextView.text = it.note ?: "(No note)"
            dateTextView.text = it.date.toString()
        }
    }
}
