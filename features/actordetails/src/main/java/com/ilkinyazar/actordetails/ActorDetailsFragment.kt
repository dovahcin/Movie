package com.ilkinyazar.actordetails

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
import com.ilkinyazar.actordetails.adapter.HorizontalMovieAdapter
import com.ilkinyazar.actordetails.databinding.FragmentActorDetailsBinding
import com.movie.android.domain.ActorDetails
import com.movie.android.domain.Movie
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
        checkForEmptyViews(actorDetails)
        binding.banner.loadImage(actorDetails.profilePath)
        binding.actor = actorDetails
    }

    private fun showKnownMovies(actorMovies: MutableList<Movie>, listId: Int) {
        if (actorMovies.size == 0) {
            binding.knownForTitle.isVisible = false
            binding.recyclerKnownFor.isVisible = false
        }
        movieAdapter.update(actorMovies, listId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isAllTextViewsGone()) {
            binding.biographyTitle.isVisible = true
            binding.biographyTitle.text = "Not much info about this artist to show."
        }
    }

    private fun isAllTextViewsGone() =
        !binding.group.isVisible

    private fun checkForEmptyViews(actorDetails: ActorDetails) {
            if(actorDetails.expireDate() == "-") binding.deathLayout.visible(false)
            if(actorDetails.birthday == "") binding.birthLayout.visible(false)
            if(actorDetails.place_of_birth == "") binding.birthPlaceLayout.visible(false)
            if(actorDetails.biography == "") {
                binding.biographyText.visible(false)
                binding.biographyTitle.visible(false)
            }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}

