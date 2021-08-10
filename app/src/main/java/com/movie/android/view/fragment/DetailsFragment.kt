package com.movie.android.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.movie.android.R
import com.movie.android.databinding.FragmentDetailsBinding
import com.movie.android.databinding.FragmentMainBinding

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       _binding = DataBindingUtil.inflate<FragmentDetailsBinding>(
           inflater, R.layout.fragment_details, container, false
       )




        return binding.root
    }

}