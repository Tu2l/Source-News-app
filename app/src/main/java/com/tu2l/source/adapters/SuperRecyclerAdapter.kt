package com.tu2l.source.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tu2l.source.R

class SuperRecyclerAdapter(
    private val adaptersList: List<RecyclerAdapter?>,
    private val namesList: List<String?>
) :
    RecyclerView.Adapter<SuperRecyclerAdapter.NewsViewHolder>() {

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nestedRecyclerView: RecyclerView? = itemView.findViewById(R.id.nested_recycler)
        val moreButton: Button? = itemView.findViewById(R.id.moreButton)
        val title: TextView? = itemView.findViewById(R.id.section_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.super_recycler_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val recyclerAdapter = adaptersList[position]
        val name = namesList[position]
        holder.title?.text = name
        holder.nestedRecyclerView?.layoutManager =
            LinearLayoutManager(holder.nestedRecyclerView?.context)
        holder.nestedRecyclerView?.adapter = recyclerAdapter
    }

    override fun getItemCount(): Int {
        return adaptersList.size
    }
}