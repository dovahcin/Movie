package com.movie.android.movielist

import android.annotation.SuppressLint
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
import com.movie.android.data.MovieListRepository.Companion.DEFAULT_PAGE
import com.movie.android.domain.ExploreItem
import com.movie.android.domain.Movie
import com.movie.android.movielist.adapter.VerticalMovieAdapter
import com.movie.android.movielist.databinding.FragmentPopularsBinding
import com.movie.android.movielist.utils.MoviesEndlessScroller
import com.movie.android.movielist.utils.visible
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment() {

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

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_populars, null, false)

        mainViewModel.getMovies(args.listId, DEFAULT_PAGE, args.movieId)

        binding.popularRecyclerView.apply {
            adapter = movieAdapter
            val layoutManager = layoutManager as LinearLayoutManager
            addOnScrollListener(MoviesEndlessScroller(layoutManager) { page ->
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

        when (args.listId) {
            ExploreItem.Type.HorizontalMovieList.ordinal -> binding.pageTitle.text = "Upcoming Movies"
            ExploreItem.Type.VerticalMovieList.ordinal -> binding.pageTitle.text = "Popular Movies"
            ExploreItem.Type.SimilarMovieList.ordinal -> binding.pageTitle.text = "Similar Movies"
            ExploreItem.Type.RecommendedMovieList.ordinal -> binding.pageTitle.text = "Recommended Movies"
            ExploreItem.Type.ActorMovieList.ordinal -> binding.pageTitle.text = "Actor Movies"
        }

        binding.back.setOnClickListener { requireActivity().onBackPressed() }

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