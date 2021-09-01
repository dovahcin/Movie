package com.movie.android.presentation.features.details.actordetails

import android.os.Bundle
import android.util.Log
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
import com.movie.android.R
import com.movie.android.databinding.FragmentActorDetailsBinding
import com.movie.android.domain.ActorDetails
import com.movie.android.domain.Movie
import com.movie.android.presentation.features.details.adapter.HorizontalMovieAdapter
import com.movie.android.presentation.utils.loadImage
import com.movie.android.presentation.utils.visible
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActorDetailsFragment : Fragment() {

    private var _binding: FragmentActorDetailsBinding? = null
    val binding get() = _binding!!

    private val args: ActorDetailsFragmentArgs by navArgs()

    private val viewModel: ActorDetailsViewModel by viewModel()

    private val showMovieClick: (Int) -> Unit = {
        findNavController().navigate(
            ActorDetailsFragmentDirections.actionActorDetailsFragmentToDetailsFragment(it)
        )
    }
    private val showMoreClick: (Int) -> Unit = { listId ->
        findNavController().navigate(
            ActorDetailsFragmentDirections.actionActorDetailsFragmentToMovieListFragment(
                listId,
                args.personId
            )
        )
        Log.d("tag", "listId : $listId personId: ${args.personId}")
    }

    private val movieAdapter = HorizontalMovieAdapter(showMovieClick, showMoreClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_actor_details, container, false)

        binding.recyclerKnownFor.adapter = movieAdapter

        viewModel.loadDataForActorDetails(args.personId, 1)

        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                when (uiState) {
                    is ActorDetailUiState.Failure -> showError(uiState.exception)
                    is ActorDetailUiState.Success -> {
                        showKnownMovies(
                            uiState.actorsDataModel.actorMovies.results,
                            uiState.actorsDataModel.actorMovies.id
                        )
                        Log.d("TAG", "id : $id")
                        showDetails(uiState.actorsDataModel.actorDetails)
                    }
                }
                showLoadingView(uiState is ActorDetailUiState.Loading)
            }
        }

        return binding.root
    }

    private fun showLoadingView(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
        binding.scrollView.isVisible = !isVisible
    }

    private fun showError(exception: Throwable) {
        Snackbar.make(binding.root, exception.localizedMessage!!, 10000).show()
    }

    private fun showDetails(actorDetails: ActorDetails) {
        if (actorDetails.expireDate() == "-") {
            binding.deathLayout.visible(false)
        }
        binding.banner.loadImage(actorDetails.profilePath)
        binding.actor = actorDetails
    }

    private fun showKnownMovies(actorMovies: MutableList<Movie>, listId: Int) {
        movieAdapter.update(actorMovies, listId)
    }
}