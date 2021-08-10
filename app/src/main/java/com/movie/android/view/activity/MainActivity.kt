package com.movie.android.view.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.movie.android.R
import com.movie.android.databinding.ActivityMainBinding
import com.movie.android.domain.Movie
import com.movie.android.utils.visible
import com.movie.android.view.adapter.PopularMovieAdapter
import com.movie.android.utils.MainUiState
import com.movie.android.utils.MainUiState.Loading
import com.movie.android.view.adapter.PopularEndlessScroller

import com.movie.android.view.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}