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

class MoodAdapter(
    private val moodList: List<MoodEntry>,
    private val onItemClick: (MoodEntry) -> Unit
) : RecyclerView.Adapter<MoodAdapter.MoodViewHolder>() {

    inner class MoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val moodIcon: ImageView = itemView.findViewById(R.id.iv_mood_icon)
        val dateText: TextView = itemView.findViewById(R.id.tv_date)
        val noteText: TextView = itemView.findViewById(R.id.tv_note)

        fun bind(moodEntry: MoodEntry) {
            dateText.text = moodEntry.date.toString()
            noteText.text = moodEntry.note.take(30) + if (moodEntry.note.length > 30) "..." else ""

            val iconRes = when(moodEntry.mood) {
                Mood.HAPPY -> R.drawable.ic_happy
                Mood.NEUTRAL -> R.drawable.ic_neutral
                Mood.SAD -> R.drawable.ic_sad
            }
            moodIcon.setImageResource(iconRes)

            itemView.setOnClickListener { onItemClick(moodEntry) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mood_entry, parent, false)
        return MoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoodViewHolder, position: Int) {
        holder.bind(moodList[position])
    }

    override fun getItemCount(): Int = moodList.size
}
