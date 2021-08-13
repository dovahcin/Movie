package com.movie.android.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EndlessScroller(
    private val layoutManager: LinearLayoutManager,
    private val loadMore: (Int) -> Unit) : RecyclerView.OnScrollListener() {

    private var loading = true
    private var previousTotal = 0
    private val visibleThreshold = 1
    private var currentPage = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)


        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (layoutManager.itemCount != previousTotal) {
                loading = false
                previousTotal = layoutManager.itemCount
            }
        }

        if (isReadyToLoadMore(totalItemCount, visibleItemCount, firstVisibleItem)) {
            currentPage++
            loadMore.invoke(currentPage)
            loading = true
        }

    }

    private fun isReadyToLoadMore(
        totalItemCount: Int,
        visibleItemCount: Int,
        firstVisibleItem: Int
    ) = !loading && (totalItemCount - visibleItemCount - firstVisibleItem <= visibleThreshold)
}
