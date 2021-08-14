package com.movie.android.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.movie.android.R
import com.movie.android.databinding.FragmentDetailsBinding
import com.movie.android.domain.details.Genre
import com.movie.android.domain.details.similar.Result
import com.movie.android.utils.DetailUiState
import com.movie.android.utils.DetailsDataModel
import com.movie.android.utils.loadImage
import com.movie.android.view.adapter.GenreAdapter
import com.movie.android.view.adapter.RecommendedAdapter
import com.movie.android.view.adapter.SimilarsAdapter
import com.movie.android.view.viewmodel.DetailsViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!


    private val detailsViewModel: DetailsViewModel by viewModel()

    private val similarsAdapter = SimilarsAdapter(mutableListOf())
    private val genreAdapter = GenreAdapter(mutableListOf())
    private val recommendedAdapter = RecommendedAdapter(mutableListOf())

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_details, container, false
        )


        val movieId = args.movieId
        detailsViewModel.loadDataForDetails(movieId)

        binding.genreRecyclerView.apply {
            adapter = genreAdapter
        }
        binding.similarMovies.apply {
            adapter = similarsAdapter
        }
        binding.recommendationMovies.apply {
            adapter = recommendedAdapter
        }

        launchStates()



        return binding.root
    }

    private fun showError(exception: Throwable) {

    }

    private fun loadAdapters(dataModel: DetailsDataModel) {
        genreAdapter.update(dataModel.details.genres as MutableList<Genre>)
        similarsAdapter.update(dataModel.similarities.results as MutableList<Result>)
        recommendedAdapter.update(dataModel.recommendations.results as MutableList<com.movie.android.domain.details.recommendation.Result>)
    }

    private fun showLoadingView(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
    }

    private fun launchStates() {
        lifecycleScope.launch {
            detailsViewModel.uiState.collect { uiState ->
                when (uiState) {
                    is DetailUiState.Failure -> showError(uiState.exception)
                    is DetailUiState.Success -> {
                        loadAdapters(uiState.detailsDataModel)
                        loadImages(
                            uiState.detailsDataModel.details.backDropPath,
                            uiState.detailsDataModel.details.posterPath
                        )
                        binding.details = uiState.detailsDataModel.details

                    }

                }
                showLoadingView(uiState is DetailUiState.Loading)
            }
        }
    }

    private fun loadImages(uriBackDropPath: String, uriPosterPath: String) {
        binding.banner.loadImage(uriBackDropPath)
        binding.miniImage.loadImage(uriPosterPath)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}