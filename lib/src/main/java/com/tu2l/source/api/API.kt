package com.tu2l.source.api

import com.google.gson.Gson
import com.tu2l.source.api.source.ANI

fun main() {
    Thread {
        val ani = ANI()
//        val newsList = ani.search("assam")
        val newsList = ani.getVideoNewsList(ANI.VIDEO_NATIONAL)
        val gson = Gson()
        for (news in newsList)
            print(gson.toJson(news)+"\n")

    }.start()
}