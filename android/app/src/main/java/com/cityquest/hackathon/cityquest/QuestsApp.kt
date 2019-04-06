package com.cityquest.hackathon.cityquest

import android.app.Application
import android.content.Context

class QuestsApp: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: QuestsApp
    }
}