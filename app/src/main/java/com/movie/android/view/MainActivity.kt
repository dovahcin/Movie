package com.movie.android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.movie.android.R
import com.movie.android.databinding.ActivityMainBinding
import com.movie.android.recyclerviews.RecyclerPopularMoviesAdapter
import com.movie.android.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity() : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private val recyclerAdapter: RecyclerPopularMoviesAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        )

        mainViewModel.loadDataForRecyclerView()

        binding.popularMoviesListRecyclerView.apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        mainViewModel.dataForPopularRecyclerViewModel.observe(this, Observer {
            recyclerAdapter.reloadData(it.results)
        })

    }
}