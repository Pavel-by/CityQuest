package com.cityquest.hackathon.cityquest.quests.quest_info

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.cityquest.hackathon.cityquest.R
import com.cityquest.hackathon.cityquest.quests.storage.Quest

class QuestLocationsRVA(): RecyclerView.Adapter<QuestLocationsRVA.QuestViewHolder>() {
    private lateinit var recycler: RecyclerView
    var quest: Quest? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    constructor(quest: Quest): this() {
        this.quest = quest
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recycler = recyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): QuestViewHolder {
        return QuestViewHolder(
            LayoutInflater.from(recycler.context).inflate(R.layout.list_item_quest_locations, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: QuestViewHolder, itemType: Int) {
        holder.locations.text = quest!!.locations.joinToString()
    }

    class QuestViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var icon = v.findViewById<ImageView>(R.id.icon)!!
        var locations = v.findViewById<TextView>(R.id.locations_text)!!
        var actionText = v.findViewById<TextView>(R.id.action_text)!!
    }
}