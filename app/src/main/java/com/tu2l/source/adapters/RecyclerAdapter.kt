package com.tu2l.source.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tu2l.source.NewsActivity
import com.tu2l.source.R
import com.tu2l.source.api.model.News

class RecyclerAdapter(private val newsList: List<News?>) :
    RecyclerView.Adapter<RecyclerAdapter.NewsViewHolder>() {

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val thumbnail: ImageView? = itemView.findViewById(R.id.thumbnail)
        val titleText: TextView? = itemView.findViewById(R.id.title)
        val storyText: TextView? = itemView.findViewById(R.id.story)
        val timestamp: TextView? = itemView.findViewById(R.id.timestamp)
        val source: TextView? = itemView.findViewById(R.id.source)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.thumbnail?.context?.let {
            Glide.with(it)
                .load(news?.imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into(holder.thumbnail)
        }
        holder.titleText?.text = """${news?.title}..."""
        holder.storyText?.text = news?.story
        holder.timestamp?.text = news?.timestamp
        holder.source?.text = news?.source

        holder.itemView.setOnClickListener {
            val intent: Intent = Intent(it.context, NewsActivity::class.java)
            intent.putExtra("url", news?.url)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}