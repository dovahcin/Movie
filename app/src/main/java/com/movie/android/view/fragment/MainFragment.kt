package com.movie.android.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.movie.android.R
import com.movie.android.databinding.FragmentMainBinding
import com.movie.android.domain.Movie
import com.movie.android.utils.EndlessScroller
import com.movie.android.utils.MainUiState
import com.movie.android.utils.visible
import com.movie.android.view.adapter.VerticalMovieAdapter
import com.movie.android.view.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModel()

    private val movieClick : (Int) -> Unit= {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToDetailsFragment(it)
        )
    }

    private val movieAdapter = VerticalMovieAdapter(mutableListOf(), movieClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, null, false)

        binding.popularMovieRecyclerView.apply {
            adapter = movieAdapter
            val layoutManager = layoutManager as LinearLayoutManager
            addOnScrollListener(EndlessScroller(layoutManager, mainViewModel::getMovies))
        }

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

    private fun showLoadingView(isVisible: Boolean) {
        binding.progressBar.visible(isVisible)
    }

    private fun showError(exception: Throwable) {
        Snackbar.make(binding.root, exception.localizedMessage!!, 10000).show()
    }

    private fun showPopularMovies(movies: MutableList<Movie>) {
        movieAdapter.update(movies)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}