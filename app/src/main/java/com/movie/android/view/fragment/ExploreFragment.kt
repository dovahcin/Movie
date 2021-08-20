package com.movie.android.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.movie.android.R
import com.movie.android.databinding.FragmentExploreBinding
import com.movie.android.utils.ExploreUiState
import com.movie.android.utils.ExploreUiState.Failure
import com.movie.android.utils.ExploreUiState.Success
import com.movie.android.view.adapter.ExploreAdapter
import com.movie.android.view.viewmodel.ExploreViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    private val exploreAdapter = ExploreAdapter()
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
        }

        lifecycleScope.launch {
            exploreViewModel.uiState.collect { uiState ->
                when(uiState) {
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