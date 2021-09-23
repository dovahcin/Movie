package com.movie.android.presentation.features.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.movie.android.R
import com.movie.android.databinding.FragmentSearchBinding
import com.movie.android.domain.SearchHistory
import com.movie.android.presentation.features.search.adapter.SearchHistoryAdapter
import com.movie.android.presentation.features.search.adapter.SearchResultAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModel()

    private val resultClick: (String, Int) -> Unit = { title, movieId ->
        viewModel.saveSearchHistory(SearchHistory(title))
        viewModel.saveInstanceState()
        findNavController().navigate(
            SearchFragmentDirections.actionSearchFragmentToDetailsFragment(movieId)
        )
    }
    private val historyClick: (String) -> Unit = {
        viewModel.loadDataForSearchList(it)
        binding.recyclerHistory.isVisible = false
        binding.recyclerSearch.isVisible = true
        binding.searchBar.setText(it)
    }

    private val deleteHistoryClick: (Int) -> Unit = {
        viewModel.deleteSearchHistory(it)
    }

    private val historyAdapter = SearchHistoryAdapter(deleteHistoryClick, historyClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        val animation = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.move)

        viewModel.loadDataForSearchList("")

        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation

        val searchBar = binding.searchBar

        val resultAdapter = SearchResultAdapter(resultClick, "")

        binding.recyclerSearch.adapter = resultAdapter
        binding.recyclerHistory.adapter = historyAdapter

        launchStates(resultAdapter)

        filterTheList(searchBar, resultAdapter)

        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }

        return binding.root
    }

    private fun launchStates(resultAdapter: SearchResultAdapter) {
        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                when (uiState) {
                    is SearchUiState.Failure -> showError(uiState.exception)
                    is SearchUiState.Success -> {
                        resultAdapter.update(uiState.searchDataModel.movies.results)
                        historyAdapter.update(uiState.searchDataModel.histories)
                    }
                }
                showLoadingView(uiState is SearchUiState.Loading)
            }
        }
    }

    private fun showError(exception: Throwable) {
        Snackbar.make(binding.root, exception.localizedMessage!!, 10000).show()
    }

    private fun showLoadingView(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
    }

    private fun filterTheList(searchBar: EditText, resultAdapter: SearchResultAdapter) {
        searchBar.addTextChangedListener(object : TextWatcher {
            private var searchFor = ""

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {
                    binding.recyclerHistory.isVisible = true
                    binding.recyclerSearch.isVisible = false
                    return
                } else {
                    binding.recyclerSearch.isVisible = true
                    binding.recyclerHistory.isVisible = false
                }

                val searchText = s.toString()
                if (searchText == searchFor)
                    return

                searchFor = searchText
                lifecycleScope.launch(Dispatchers.IO) {
                    delay(300)
                    if (searchFor != searchText)
                        return@launch

                    viewModel.loadDataForSearchList(searchFor, true)
                    viewModel.deleteInstanceState()
                    resultAdapter.updateText(searchFor)
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}