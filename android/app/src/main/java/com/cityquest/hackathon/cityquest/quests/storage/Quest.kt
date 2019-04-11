package com.cityquest.hackathon.cityquest.quests.storage

class Quest(var id: Int,
            var name: String,
            var tags: List<String>,
            var types: List<String>,
            var points: Int,
            var rating: Float) {
    var description: String? = null
    var locations: List<String> = ArrayList()
}