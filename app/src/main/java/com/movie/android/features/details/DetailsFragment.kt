package com.movie.android.features.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.movie.android.R
import com.movie.android.databinding.FragmentDetailsBinding
import com.movie.android.domain.Genre
import com.movie.android.utils.loadImage
import com.movie.android.utils.visible
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val detailsViewModel: DetailsViewModel by viewModel()

    private val movieClick : (Int) -> Unit = {
        findNavController().navigate(
            DetailsFragmentDirections.actionDetailsFragmentToDetailsFragment(
                it
            )
        )
    }

    private val showMoreClick : (Int) -> Unit= {
        /*Navigate to main list with a specific input*/
    }

    private val horizontalMovieAdapter1 =
        HorizontalMovieAdapter(itemClick = movieClick, showMoreClick= showMoreClick)
    private val horizontalMovieAdapter2 =
        HorizontalMovieAdapter(itemClick = movieClick, showMoreClick = showMoreClick)

    private val genreAdapter = GenreAdapter()

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        detailsViewModel.loadDataForDetails(args.movieId)

        binding.genreRecyclerView.apply {
            adapter = genreAdapter
        }
        binding.horizontalList1.apply {
            adapter = horizontalMovieAdapter1
        }
        binding.horizontalList2.apply {
            adapter = horizontalMovieAdapter2
        }

        launchStates()

        binding.back.setOnClickListener { requireActivity().onBackPressed() }
        return binding.root
    }

    private fun showError(exception: Throwable) {
        Snackbar.make(binding.root, exception.localizedMessage!!, 10000).show()
    }

    private fun loadAdapters(dataModel: DetailsDataModel) {
        genreAdapter.update(dataModel.details.genres as MutableList<Genre>)
        horizontalMovieAdapter1.update(dataModel.similarities.results)
        horizontalMovieAdapter2.update(dataModel.recommendations.results)
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

    private fun showLoadingView(isVisible: Boolean) {
        binding.apply {
            progressBar.visible(isVisible)
            scrollContainer.visible(!isVisible)
        }
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