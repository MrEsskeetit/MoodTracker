package com.moodtracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moodtracker.R
import com.moodtracker.adapter.MoodAdapter
import com.moodtracker.data.FakeMoodRepository
import androidx.navigation.fragment.findNavController

class MoodHistoryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mood_history, container, false)

        recyclerView = view.findViewById(R.id.rv_mood_list)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = MoodAdapter(FakeMoodRepository.moodList) { moodEntry ->
            val action = MoodHistoryFragmentDirections
                .actionMoodHistoryFragmentToMoodDetailsFragment(moodEntry.id.toString())
            findNavController().navigate(action)
        }
        recyclerView.adapter = adapter

        return view
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}
