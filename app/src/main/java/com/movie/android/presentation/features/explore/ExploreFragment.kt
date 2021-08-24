package com.movie.android.presentation.features.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.movie.android.R
import com.movie.android.databinding.FragmentExploreBinding
import com.movie.android.domain.ExploreItem.Type
import com.movie.android.domain.Movie
import com.movie.android.presentation.features.explore.ExploreUiState.Failure
import com.movie.android.presentation.features.explore.ExploreUiState.Success
import com.movie.android.presentation.features.explore.adapter.ExploreAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    private val showAllClick: (Type) -> Unit = {
        findNavController().navigate(
            ExploreFragmentDirections.actionExploreFragmentToMovieListFragment(it.ordinal)
        )
    }

    private val showMovieClick: (Movie) -> Unit = {
        findNavController().navigate(
            ExploreFragmentDirections.actionExploreFragmentToDetailsFragment(it.id)
        )
    }

    private val exploreAdapter = ExploreAdapter(showAllClick, showMovieClick)
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