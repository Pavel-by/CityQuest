package com.cityquest.hackathon.cityquest.quests.storage

import android.graphics.drawable.Drawable
import com.cityquest.hackathon.cityquest.QuestsApp
import com.cityquest.hackathon.cityquest.R
import com.cityquest.hackathon.cityquest.quests.QuestsListRequest
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class QuestsService {
    var quests = arrayListOf(
        Quest("1", "Король львов", listOf("Лев", "Долгий"), listOf("Архитектура"), "10-300", 4.5f, d(R.drawable.test_lion)),
        Quest("2", "Дворцовая площадь", listOf("Памятники"), listOf("Архитектура"), "15", 4f, d(R.drawable.test_palace_square)),
        Quest("3", "Театрал", listOf("Театр"), listOf("Архитектура"), "30", 4.2f, d(R.drawable.test_theatre)),
        Quest("4", "Сила мутаций", listOf("История", "Странное", "Музей"), listOf(), "40", 5f, d(R.drawable.test_curiosities)),
        Quest("5", "Морские ворота", listOf("Море", "Порт", "Крепости"), listOf("Архитектура"), "20", 4.4f, d(R.drawable.test_kronshtadt)),
        Quest("6", "Аврора", listOf("Море", "Музей", "Корабли"), listOf(), "30", 5f, d(R.drawable.test_avrora)),
        Quest("7", "Забудь про тьму", listOf("Время", "Сезонный", "Природа"), listOf(), "10", 3.4f, d(R.drawable.test_white_night)),
        Quest("8", "Крепка ли твоя вера?", listOf("Собор", "Православие"), listOf("Архитектура"), "30", 4.5f, d(R.drawable.test_sobor))
    )
    private var questsRequest: QuestsListRequest? = null

    fun d(drawable: Int): Drawable? {
        return QuestsApp.instance.getDrawable(drawable)
    }

    fun quests(): Observable<List<Quest>> {
        return quests(null)
    }

    fun quests(params: QuestsListRequest.SearchParams?): Observable<List<Quest>> {
        return QuestsListRequest(params)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun initQuestsRequest(): QuestsListRequest {
        this.questsRequest = QuestsListRequest()
        return questsRequest!!
    }

    fun startQuest(questId: Int) {

    }

    companion object {
        var instance = QuestsService()
    }
}