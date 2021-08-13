package com.movie.android.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.movie.android.R
import com.movie.android.databinding.FragmentDetailsBinding
import com.movie.android.utils.screenWidth
import kotlin.math.roundToInt

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_details, container, false
        )

        binding.minorDetailImageView.layoutParams.width = (screenWidth() * 0.35).roundToInt()
//        binding.detailRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)





        return binding.root
    }

}