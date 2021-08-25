package com.movie.android.presentation.features.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.movie.android.R
import com.movie.android.databinding.FragmentPopularsBinding
import com.movie.android.domain.Movie
import com.movie.android.presentation.features.movielist.adapter.VerticalMovieAdapter
import com.movie.android.presentation.utils.EndlessScroller
import com.movie.android.presentation.utils.visible
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment() {
    companion object {
        private const val DEFAULT_PAGE = 1
    }

    private var _binding: FragmentPopularsBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MovieListViewModel by viewModel()

    private val args: MovieListFragmentArgs by navArgs()

    private val movieClick : (Movie) -> Unit= {
        findNavController().navigate(
            MovieListFragmentDirections.actionMovieListFragmentToDetailsFragment(it.id)
        )
    }

    private val movieAdapter = VerticalMovieAdapter(mutableListOf(), movieClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_populars, null, false)

        mainViewModel.getMovies(args.listId, DEFAULT_PAGE, args.movieId)

        binding.popularRecyclerView.apply {
            adapter = movieAdapter
            val layoutManager = layoutManager as LinearLayoutManager
            addOnScrollListener(EndlessScroller(layoutManager) { page ->
                mainViewModel.getMovies(args.listId, page, args.movieId)
            })
        }

        lifecycleScope.launch {
            mainViewModel.uiState.collect { uiState ->
                when (uiState) {
                    is MovieListUiState.Success -> showPopularMovies(uiState.movies.results)
                    is MovieListUiState.Error -> showError(uiState.exception)
                }
                showLoadingView(uiState is MovieListUiState.Loading)
            }
        }

        return binding.root
    }

    private fun showLoadingView(isVisible: Boolean) {
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

}