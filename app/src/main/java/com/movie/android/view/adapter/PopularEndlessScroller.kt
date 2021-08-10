package com.movie.android.view.adapter

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PopularEndlessScroller(private val linearLayoutManager: LinearLayoutManager,
                             private val loadMore: LoadMore
) : RecyclerView.OnScrollListener() {

    var loading = true
    var previousTotal = 0
    val visibleThreshold = 1
    var currentPage = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)


        val visibleItemCount = linearLayoutManager.childCount
        val totalItemCount = linearLayoutManager.itemCount
        val firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount != previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }

        }

        if (!loading && (totalItemCount - visibleItemCount - firstVisibleItem <= visibleThreshold)) {
            currentPage++

            loadMore.onLoadMore(currentPage)

            loading = true

        }

    }

    interface LoadMore {
        fun onLoadMore(currentPage: Int)
    }

}
