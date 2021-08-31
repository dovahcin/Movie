package com.movie.android.presentation.features.details.actordetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.movie.android.R
import com.movie.android.databinding.FragmentActorDetailsBinding
import com.movie.android.domain.Actor
import com.movie.android.domain.ActorDetails
import com.movie.android.domain.Movie
import com.movie.android.presentation.features.details.actordetails.adapter.ActorKnownAdapter
import com.movie.android.presentation.utils.loadImage
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActorDetailsFragment : Fragment() {

    private var _binding: FragmentActorDetailsBinding? = null
    val binding get() = _binding!!

    private val args: ActorDetailsFragmentArgs by navArgs()

    private val viewModel: ActorDetailsViewModel by viewModel()

    private val knownAdapter = ActorKnownAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_actor_details, container, false)

        binding.recyclerKnownFor.adapter = knownAdapter

        viewModel.loadDataForActorDetails(args.personId, 1)

        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                when (uiState) {
                    is ActorDetailUiState.Failure -> showError(uiState.exception)
                    is ActorDetailUiState.Success -> {
                        showKnownMovies(uiState.actorsDataModel.actorMovies)
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
        binding.banner.loadImage(actorDetails.profilePath)
        binding.actor = actorDetails
    }

    private fun showKnownMovies(actorMovies: List<Movie>) {
        knownAdapter.update(actorMovies.toMutableList())
    }
}