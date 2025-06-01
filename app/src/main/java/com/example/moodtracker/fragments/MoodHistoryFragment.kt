package com.moodtracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moodtracker.R
import com.moodtracker.adapter.MoodAdapter
import com.moodtracker.data.FakeMoodRepository

class MoodHistoryFragment : Fragment() {

    private val repository = FakeMoodRepository()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mood_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        repository.getAllMoods().observe(viewLifecycleOwner, Observer { moods ->
            adapter = MoodAdapter(moods) { moodEntry ->
                val action = MoodHistoryFragmentDirections
                    .actionMoodHistoryFragmentToMoodDetailsFragment(moodEntry.id.toString())
                findNavController().navigate(action)
            }
            recyclerView.adapter = adapter
        })
    }
}
