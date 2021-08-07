package com.tu2l.source.api.source

import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

open class _Base {
    protected fun getDocument(url: String): Document? {
        var response: Connection.Response? = null
        try {
            response = Jsoup.connect(url).execute()
        } catch (ex: java.lang.Exception) {
            ex.printStackTrace()
        }


        return response?.parse()
    }

}