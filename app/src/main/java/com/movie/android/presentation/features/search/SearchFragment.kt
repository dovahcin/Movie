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
import com.movie.android.domain.History
import com.movie.android.presentation.features.search.adapter.HistoryAdapter
import com.movie.android.presentation.features.search.adapter.SearchResultAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModel()

    private val resultClick: (String, Int) -> Unit = { title, id ->
        viewModel.insertTitles(History(title, id))
        findNavController().navigate(
            SearchFragmentDirections.actionSearchFragmentToDetailsFragment(id)
        )
    }

    private val deleteClick: (Int) -> Unit = {
        viewModel.deleteTitle(it)
        viewModel.loadDataForSearchList("0")
    }

    private val resultAdapter = SearchResultAdapter(resultClick)
    private val historyAdapter = HistoryAdapter(deleteClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        val animation = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.move)

        viewModel.loadDataForSearchList("0")

        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation

        val searchBar = binding.searchBar

        binding.recyclerSearch.adapter = resultAdapter
        binding.recyclerHistory.adapter = historyAdapter

        launchStates()

        filterTheList(searchBar)

        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }

        return binding.root
    }

    private fun launchStates() {
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

    private fun filterTheList(searchBar: EditText) {
        searchBar.addTextChangedListener(object : TextWatcher {
            private var searchFor = ""

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() == "") {
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
                CoroutineScope(Dispatchers.Main).launch {
                    delay(300)
                    if (searchFor != searchText)
                        return@launch

                    viewModel.loadDataForSearchList(searchFor)
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