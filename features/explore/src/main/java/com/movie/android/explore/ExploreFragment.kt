package com.movie.android.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.movie.android.domain.Actor
import com.movie.android.domain.ExploreItem.Type
import com.movie.android.domain.Movie
import com.google.android.material.snackbar.Snackbar
import com.movie.android.explore.ExploreUiState.Failure
import com.movie.android.explore.ExploreUiState.Success
import com.movie.android.explore.adapter.ExploreAdapter
import com.movie.android.explore.databinding.FragmentExploreBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    private val showAllMoviesClick: (Type) -> Unit = { type ->
        findNavController().navigate(
            ExploreFragmentDirections.actionExploreFragmentToMovieListFragment(type.ordinal, -1)
        )
    }
    private val showAllActorsClick: () -> Unit = {
        findNavController().navigate(
            ExploreFragmentDirections.actionExploreFragmentToActorListFragment()
        )
    }

    private val showMovieClick: (Movie) -> Unit = {
        findNavController().navigate(
            ExploreFragmentDirections.actionExploreFragmentToDetailsFragment(it.id)
        )
    }
    private val showActorClick: (Actor) -> Unit = {
        findNavController().navigate(
            ExploreFragmentDirections.actionExploreFragmentToActorDetailsFragment(it.id)
        )
    }

    private val exploreAdapter = ExploreAdapter(showAllMoviesClick,showAllActorsClick, showActorClick, showMovieClick)
    private val exploreViewModel: ExploreViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_explore, container, false)

        exploreViewModel.loadDataForExplore(1)

        binding.exploreRecyclerView.apply {
            adapter = exploreAdapter
            layoutManager = LinearLayoutManager(context)
        }

        lifecycleScope.launch {
            exploreViewModel.uiState.collect { uiState ->
                when (uiState) {
                    is Failure -> showError(uiState.exception)
                    is Success -> {
                        exploreAdapter.update(uiState.explore.items)
                    }
                }
                showLoadingView(uiState is ExploreUiState.Loading)
            }
        }

        binding.searchBar.setOnClickListener {
            val extras = FragmentNavigatorExtras(
                binding.searchBar to "search_bar"
            )
            findNavController().navigate(
                ExploreFragmentDirections.actionExploreFragmentToSearchFragment(), extras
            )
        }

        return binding.root
    }

    private fun showError(exception: Throwable) {
        Snackbar.make(binding.root, exception.localizedMessage!!, 10000).show()
    }

    private fun showLoadingView(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
        binding.exploreRecyclerView.isVisible = !isVisible
    }


}