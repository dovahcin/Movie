package com.movie.android.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.movie.android.R
import com.movie.android.databinding.ActivityMainBinding
import com.movie.android.domain.Movie
import com.movie.android.utils.visible
import com.movie.android.view.adapter.PopularMovieAdapter
import com.movie.android.viewmodel.MainUiState
import com.movie.android.viewmodel.MainUiState.Loading
import com.movie.android.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModel()
    private val movieAdapter: PopularMovieAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.popularMoviesListRecyclerView.adapter = movieAdapter

        mainViewModel.loadDataForRecyclerView()
        lifecycleScope.launch {
            mainViewModel.uiState.collect {uiState->
                when (uiState) {
                    is MainUiState.Success -> showPopularMovies(uiState.movies)
                    is MainUiState.Error -> showError(uiState.exception)
                }
                showLoadingView(uiState is Loading)
            }
        }

    }

    private fun showLoadingView(isVisible: Boolean) {
        Log.i("tag" , "isVisible: $isVisible")
        binding.progressBar.visible(isVisible)
    }

    private fun showError(exception: Throwable) {
        Snackbar.make(binding.root,exception.localizedMessage!!,Snackbar.LENGTH_SHORT).show()
    }

    private fun showPopularMovies(movies: List<Movie>) {
        movieAdapter.update(movies)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}