package com.tu2l.source

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.tu2l.source.api.model.News
import com.tu2l.source.api.source.ANI

class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val url = intent?.getStringExtra("url")

        if (url != null || url != "") {
            Thread {
                val news = ANI().getNews(url)

                runOnUiThread {
                    init(news)
                }
            }.start()
        } else {
            finish()
        }
    }

    private fun init(news: News) {
        val thumbnail = findViewById<ImageView>(R.id.thumbnail)
        Glide.with(this)
            .load(news.imageUrl)
            .placeholder(R.mipmap.ic_launcher)
            .centerCrop()
            .into(thumbnail)

        val titleText = findViewById<TextView>(R.id.title)
        titleText.text = news.title

        val storyText = findViewById<TextView>(R.id.story)
        storyText.text = news.story

        val imageTitleText = findViewById<TextView>(R.id.image_title)
        imageTitleText.text = news.imageTitle

        val sourceText = findViewById<TextView>(R.id.source)
        sourceText.text = news.source

        val timestampText = findViewById<TextView>(R.id.timestamp)
        timestampText.text = news.timestamp

    }
}