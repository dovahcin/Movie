package com.movie.android.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.movie.android.moviedetails.adapter.GenreAdapter
import com.movie.android.moviedetails.adapter.HorizontalMovieAdapter
import com.movie.android.moviedetails.databinding.FragmentMovieDetailsBinding
import com.movie.android.domain.DetailsDataModel
import com.movie.android.domain.Genre
import com.movie.android.domain.Movie
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsFragment : Fragment() {

    private val args: MovieDetailsFragmentArgs by navArgs()

    private var _binding: FragmentMovieDetailsBinding? = null

    private val binding get() = _binding!!
    private val detailsViewModel: MovieDetailsViewModel by viewModel()

    private val movieClick: (Int) -> Unit = {
        findNavController().navigate(
            MovieDetailsFragmentDirections.actionDetailsFragmentToDetailsFragment(
                it
            )
        )
    }
    private val genreAdapter = GenreAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)

        val showMoreClick: (Int) -> Unit = { listId ->
            findNavController().navigate(
                MovieDetailsFragmentDirections.actionDetailsFragmentToMovieListFragment(
                    listId,
                    args.movieId
                )
            )
        }

        val horizontalSimilarAdapter =
            HorizontalMovieAdapter(
                movieClick,
                showMoreClick
            )
        val horizontalRecommendAdapter =
            HorizontalMovieAdapter(
                movieClick,
                showMoreClick
            )

        detailsViewModel.loadDataForDetails(args.movieId)
        binding.apply {
            genreRecyclerView.apply {
                adapter = genreAdapter
            }
            horizontalSimilarsList.apply {
                adapter = horizontalSimilarAdapter
            }
            horizontalRecommendationList.apply {
                adapter = horizontalRecommendAdapter
            }
        }

        launchStates(horizontalSimilarAdapter, horizontalRecommendAdapter)

        binding.back.setOnClickListener { requireActivity().onBackPressed() }

        return binding.root
    }

    private fun showError(exception: Throwable) {
        Snackbar.make(binding.root, exception.localizedMessage!!, 10000).show()
    }

    private fun loadAdapters(
        dataModel: DetailsDataModel,
        horizontalSimilarAdapter: HorizontalMovieAdapter,
        horizontalRecommendAdapter: HorizontalMovieAdapter
    ) {
        genreAdapter.update(dataModel.details.genres as MutableList<Genre>)
        dataModel.similarities.run {
            horizontalSimilarAdapter.update(results, id)
        }
        dataModel.recommendations.run {
            horizontalRecommendAdapter.update(results, id)
        }

    }

    private fun launchStates(
        horizontalSimilarAdapter: HorizontalMovieAdapter,
        horizontalRecommendAdapter: HorizontalMovieAdapter
    ) {
        lifecycleScope.launch {
            detailsViewModel.uiState.collect { uiState ->
                when (uiState) {
                    is MovieDetailUiState.Failure -> showError(uiState.exception)
                    is MovieDetailUiState.Success -> {
                        showTitles(
                            uiState.detailsDataModel.recommendations.results,
                            uiState.detailsDataModel.similarities.results
                        )
                        loadAdapters(
                            uiState.detailsDataModel,
                            horizontalSimilarAdapter,
                            horizontalRecommendAdapter
                        )
                        loadImages(
                            uiState.detailsDataModel.details.backDropPath,
                            uiState.detailsDataModel.details.posterPath
                        )
                        binding.details = uiState.detailsDataModel.details
                    }
                }
                showLoadingView(uiState is MovieDetailUiState.Loading)
            }
        }
    }

    private fun showLoadingView(isVisible: Boolean) {
        binding.apply {
            progressBar.visible(isVisible)
            scrollContainer.visible(!isVisible)
        }
    }

    private fun showTitles(
        recommendedMovies: MutableList<Movie>,
        similarMovies: MutableList<Movie>
    ) {
        if (recommendedMovies.size == 0)
            binding.recommendationTitle.isVisible = false
        if (similarMovies.size == 0)
            binding.similarTitle.isVisible = false
    }

    private fun loadImages(uriBackDropPath: String, uriPosterPath: String) {
        binding.banner.loadImage(uriBackDropPath)
        binding.miniImage.loadImage(uriPosterPath)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}