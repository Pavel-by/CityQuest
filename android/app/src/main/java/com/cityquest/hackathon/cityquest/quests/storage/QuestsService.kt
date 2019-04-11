package com.cityquest.hackathon.cityquest.quests.storage

class QuestsService {
    var quests = arrayListOf(
        Quest(1, "Quest 1", listOf("tag1", "tag2"), listOf("Architecture"), 10, 4.3f),
        Quest(2, "Quest 2", listOf("tag3"), listOf("Architecture"), 5, 5f),
        Quest(3, "Quest 3", listOf("tag1"), listOf("Running"), 14, 2f),
        Quest(4, "Quest 4", listOf("tag2"), listOf("Architecture", "Running"), 10, 3f),
        Quest(5, "Quest 5", listOf("tag1", "tag2", "tag3"), listOf("Museum"), 32, 4.3f),
        Quest(6, "Quest 6", listOf("tag7"), listOf("Food"), 11, 4.7f)
    )

    fun getQuests(): List<Quest> {
        return quests
    }

    fun getQuestById(id: Int): Quest? {
        for (quest in quests)
            if (quest.id == id)
                return quest
        return null
    }

    fun startQuest(questId: Int) {

    }

    companion object {
        var instance = QuestsService()
    }
}