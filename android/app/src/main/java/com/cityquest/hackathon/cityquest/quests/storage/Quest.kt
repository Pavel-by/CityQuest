package com.cityquest.hackathon.cityquest.quests.storage

import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import com.google.gson.annotations.SerializedName

class Quest(
    var id: String,
    var name: String,
    var tags: List<String>,
    @SerializedName("quest_types")
    var types: List<String>,
    var points: String = "",
    var rating: Float = 0f,
    var icon: Drawable? = null,
    var start: String? = null,
    var description: String? = null,
    var locations: List<String> = ArrayList()
)