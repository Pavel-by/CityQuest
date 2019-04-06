package com.cityquest.hackathon.cityquest.quests

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cityquest.hackathon.cityquest.R
import com.cityquest.hackathon.cityquest.quests.quest_info.QuestInfoActivity
import com.cityquest.hackathon.cityquest.quests.storage.Quest
import com.cityquest.hackathon.cityquest.quests.storage.QuestsService


class QuestsFragment: Fragment() {
    lateinit var recycler: RecyclerView
    lateinit var adapter: QuestsRVA
    var listListener = object: QuestsClickListener {
        override fun onClick(v: View, index: Int, quest: Quest) {
            QuestInfoActivity.start(quest.id)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_quests, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler = view.findViewById(R.id.quests_recycler)
        adapter = QuestsRVA()
        adapter.listener = listListener

        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context)

        adapter.setQuests(QuestsService.instance.getQuests())
    }
}