package com.cityquest.hackathon.cityquest.quests

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.cityquest.hackathon.cityquest.R
import com.cityquest.hackathon.cityquest.quests.storage.Quest
import me.gujun.android.taggroup.TagGroup
import kotlin.random.Random
import kotlin.random.nextUInt

interface QuestsClickListener {
    fun onClick(v: View, index: Int, quest: Quest)
}

class QuestsRVA : RecyclerView.Adapter<QuestsRVA.QuestViewHolder>() {
    private val quests = ArrayList<Quest>();
    private var recycler: RecyclerView? = null
    private var icon: Drawable? = null
    var listener: QuestsClickListener? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recycler = recyclerView
        this.icon = recyclerView.context.getDrawable(R.mipmap.ic_launcher_round)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestViewHolder {
        return QuestViewHolder(LayoutInflater.from(recycler!!.context).inflate(R.layout.list_item_quest, parent, false))
    }

    override fun getItemCount(): Int {
        return quests.size
    }

    override fun onBindViewHolder(holder: QuestViewHolder, index: Int) {
        val quest = quests.get(index)
        holder.icon.setImageDrawable(
            if (quest.icon != null) quest.icon!!
            else icon
        )
        holder.name.text = quest.name
        holder.points.text = quest.points.toString()
        holder.rating.rating = quest.rating
        holder.tags.setTags(quest.types + quest.tags)
        holder.locationsCount.text = Random.nextInt(1, 10).toString()
        holder.itemView.setOnClickListener{
            if (listener != null) listener!!.onClick(it, index, quest)
        }
    }

    fun setQuests(quests: List<Quest>) {
        this.quests.clear()
        this.quests.addAll(quests)
        notifyDataSetChanged()
    }

    fun addQuests(quests: List<Quest>) {
        this.quests.addAll(quests)
        notifyItemRangeInserted(this.quests.size - quests.size, quests.size)
    }

    fun removeQuests() {
        this.quests.clear()
        notifyDataSetChanged()
    }

    class QuestViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var icon: ImageView
        var name: TextView
        var tags: TagGroup
        var points: TextView
        var locationsCount: TextView
        var rating: RatingBar

        init {
            icon = v.findViewById(R.id.icon)
            name = v.findViewById(R.id.name)
            tags = v.findViewById(R.id.tags)
            points = v.findViewById(R.id.points_count_text)
            rating = v.findViewById(R.id.rating)
            locationsCount = v.findViewById(R.id.locations_count_text)
        }
    }
}