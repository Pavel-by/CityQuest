package com.cityquest.hackathon.cityquest.quests.quest_info

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.cityquest.hackathon.cityquest.QuestsApp
import com.cityquest.hackathon.cityquest.R
import com.cityquest.hackathon.cityquest.quests.storage.Quest
import com.cityquest.hackathon.cityquest.quests.storage.QuestsService
import kotlinx.android.synthetic.main.activity_quest_info.*

class QuestInfoActivity: AppCompatActivity() {
    var quest: Quest? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest_info)
        if (intent.extras == null || !intent.extras!!.containsKey(QUEST_ID)) {
            finish()
            return
        }

        quest = QuestsService.instance.getQuestById(intent.extras!!.getInt(QUEST_ID))
        if (quest == null) {
            finish()
            return
        }

        quest!!.description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
        quest!!.locations = listOf("Торжковская 15", "Пионерская 1", "Адмиралтейская 10")

        toolbar.title = quest!!.name
        toolbar.setTitleTextColor(resources.getColor(R.color.toolbarContentColor))
        toolbar.navigationIcon = getDrawable(R.mipmap.ic_back)
        toolbar.setNavigationOnClickListener { finish() }

        button_start.setOnClickListener { QuestsService.instance.startQuest(quest!!.id) }

        quest_icon.setImageDrawable(getDrawable(R.mipmap.ic_launcher_round))
        quest_type.text = quest!!.types.joinToString()
        quest_tags.setTags(quest!!.tags)
        quest_locations_list.adapter = QuestLocationsRVA(quest!!)
        quest_locations_list.layoutManager = LinearLayoutManager(this)
        quest_description.text = quest!!.description
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return true
    }

    companion object {
        const val QUEST_ID = "quest_id"

        fun start(id: Int) {
            val intent = Intent(QuestsApp.instance, QuestInfoActivity::class.java)
            intent.putExtra(QUEST_ID, id)
            QuestsApp.instance.startActivity(intent)
        }
    }
}