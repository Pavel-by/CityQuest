package com.cityquest.hackathon.cityquest

import android.app.Application
import android.content.Context
import android.content.Intent
import com.cityquest.hackathon.cityquest.map.RouteService

class QuestsApp: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        startService(Intent(this, RouteService::class.java))
    }

    companion object {
        lateinit var instance: QuestsApp
    }
}