package com.movie.android.actorlist

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
import com.movie.android.actorlist.adapter.VerticalActorAdapter
import com.movie.android.actorlist.databinding.FragmentActorListBinding
import com.google.android.material.snackbar.Snackbar
import com.movie.android.movielist.utils.EndlessScroller
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActorListFragment : Fragment() {
    private val defaultPage = 1

    private var _binding: FragmentActorListBinding? = null
    private val binding get() = _binding!!

    private val actorClick: (Int) -> Unit = { id ->
        findNavController().navigate(
            ActorListFragmentDirections.actionActorListFragmentToActorDetailsFragment(id)
        )
    }

    private val viewModel: ActorListViewModel by viewModel()

    private val actorsAdapter = VerticalActorAdapter(actorClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_actor_list, container, false)

        viewModel.getActors(defaultPage)

        binding.recyclerView.apply {
            adapter = actorsAdapter
            val layoutManager = layoutManager as LinearLayoutManager
            addOnScrollListener(EndlessScroller(layoutManager) { page ->
                viewModel.getActors(page)
            })
        }

        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                when(uiState) {
                    is ActorListUiState.Failure -> showError(uiState.exception)
                    is ActorListUiState.Success -> actorsAdapter.update(uiState.actorListModel.results)
                }
                showLoadingView(uiState is ActorListUiState.Loading)
            }
        }
        binding.back.setOnClickListener { requireActivity().onBackPressed() }
        return binding.root
    }

    private fun showLoadingView(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
    }

    private fun showError(exception: Throwable) {
        Snackbar.make(binding.root, exception.localizedMessage!!, 10000).show()
    }
}