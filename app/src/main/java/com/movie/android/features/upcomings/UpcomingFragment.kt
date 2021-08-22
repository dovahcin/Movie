package com.movie.android.features.upcomings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.movie.android.R
import com.movie.android.databinding.FragmentUpcomingBinding
import com.movie.android.domain.Movie
import com.movie.android.features.popular.VerticalMovieAdapter
import com.movie.android.utils.visible
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpcomingFragment : Fragment() {

    private var _binding: FragmentUpcomingBinding? = null
    val binding get() = _binding!!

    private val movieClick: (Int) -> Unit = {
        TODO()
    }

    private val upcomingViewModel: UpcomingViewModel by viewModel()
    private val upcomingAdapter = VerticalMovieAdapter(itemClick = movieClick)

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_upcoming, container, false)

        binding.upcomingRecyclerView.apply {
            adapter = upcomingAdapter
        }

        upcomingViewModel.loadDataForList(1)

        lifecycleScope.launch {
            upcomingViewModel.uiState.collect { uiState ->
                when (uiState) {
                    is UpcomingUiState.Failure -> showError(uiState.exception)
                    is UpcomingUiState.Success -> upcomingAdapter.update(uiState.movies as MutableList<Movie>)
                }
                showLoadingView(uiState is UpcomingUiState.Loading)
            }
        }

        return binding.root
    }

    private fun showLoadingView(isVisible: Boolean) {
        binding.progressBar.visible(isVisible)
    }

    private fun showError(exception: Throwable) {
        Snackbar.make(binding.root, exception.localizedMessage!!, 10000).show()
    }

}