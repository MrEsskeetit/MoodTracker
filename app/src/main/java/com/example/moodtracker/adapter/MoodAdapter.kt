package com.moodtracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moodtracker.R
import com.moodtracker.data.MoodEntry
import com.moodtracker.data.Mood
import java.text.SimpleDateFormat
import java.util.*

class MoodAdapter(
    private val moodList: List<MoodEntry>,
    private val onClick: (MoodEntry) -> Unit
) : RecyclerView.Adapter<MoodAdapter.MoodViewHolder>() {

    inner class MoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val moodIcon: ImageView = itemView.findViewById(R.id.mood_icon)
        private val moodNote: TextView = itemView.findViewById(R.id.mood_note)
        private val moodDate: TextView = itemView.findViewById(R.id.mood_date)

        fun bind(moodEntry: MoodEntry) {
            moodIcon.setImageResource(getMoodIcon(moodEntry.mood))
            moodNote.text = moodEntry.note ?: "(No note)"
            moodDate.text = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault()).format(moodEntry.date)

            itemView.setOnClickListener { onClick(moodEntry) }
        }

        private fun getMoodIcon(mood: Mood): Int = when (mood) {
            Mood.HAPPY -> R.drawable.ic_happy
            Mood.SAD -> R.drawable.ic_sad
            Mood.ANGRY -> R.drawable.ic_angry
            Mood.NEUTRAL -> R.drawable.ic_neutral
            Mood.EXCITED -> R.drawable.ic_excited
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mood, parent, false)
        return MoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoodViewHolder, position: Int) {
        holder.bind(moodList[position])
    }

    override fun getItemCount() = moodList.size
}
