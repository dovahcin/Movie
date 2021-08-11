package com.movie.android.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.movie.android.R
import com.movie.android.databinding.FragmentMainBinding
import com.movie.android.domain.popular.Movie
import com.movie.android.utils.MainUiState
import com.movie.android.utils.visible
import com.movie.android.view.adapter.PopularEndlessScroller
import com.movie.android.view.adapter.PopularMovieAdapter
import com.movie.android.view.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment(), PopularEndlessScroller.LoadMore {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModel()
    private val movieAdapter: PopularMovieAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, null, false)


        binding.popularMoviesListRecyclerView.adapter = movieAdapter


        mainViewModel.loadDataForGeneralList("1")

        lifecycleScope.launch {
            mainViewModel.uiState.collect { uiState ->
                when (uiState) {
                    is MainUiState.Success -> showPopularMovies(uiState.movies)
                    is MainUiState.Error -> showError(uiState.exception)
                }
                showLoadingView(uiState is MainUiState.Loading)
            }
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        binding.popularMoviesListRecyclerView.layoutManager = layoutManager

        val popularEndlessScroller = PopularEndlessScroller(layoutManager, this)

        binding.popularMoviesListRecyclerView.addOnScrollListener(popularEndlessScroller)
    }

    private fun showLoadingView(isVisible: Boolean) {
        Log.i("tag", "isVisible: $isVisible")
        binding.progressBar.visible(isVisible)
    }

    private fun showError(exception: Throwable) {
        Snackbar.make(binding.root, exception.localizedMessage!!, Snackbar.LENGTH_SHORT).show()
    }

    private fun showPopularMovies(movies: MutableList<Movie>) {
        movieAdapter.update(movies)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onLoadMore(currentPage: Int) {
        mainViewModel.loadDataForGeneralList(currentPage.toString())
    }

}