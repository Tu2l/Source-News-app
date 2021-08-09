package com.tu2l.source

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tu2l.source.adapters.RecyclerAdapter
import com.tu2l.source.api.source.ANI


private const val URL_PARAM = "url"

class NewsListFragment : Fragment() {
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(URL_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.main_recycler)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val refreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.refresh_layout)
        refreshLayout.setOnRefreshListener {
            initNewsAdapter(recyclerView, refreshLayout)
        }

        initNewsAdapter(recyclerView, refreshLayout)

    }

    private fun initNewsAdapter(recyclerView: RecyclerView, refreshLayout: SwipeRefreshLayout) {
        refreshLayout.isRefreshing = true
        Thread {
            if (url != null) {
                try {
                    val adapter = RecyclerAdapter(ANI().getNewsList(url!!))
                    activity?.runOnUiThread {
                        recyclerView.adapter = adapter
                        refreshLayout.isRefreshing = false
                    }
                } catch (ex: Exception) {
                    activity?.runOnUiThread {
                        Toast.makeText(
                            context,
                            "Something went wrong while loading news",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

            }
        }.start()
    }

    companion object {
        @JvmStatic
        fun newInstance(url: String) =
            NewsListFragment().apply {
                arguments = Bundle().apply {
                    putString(URL_PARAM, url)
                }
            }
    }
}