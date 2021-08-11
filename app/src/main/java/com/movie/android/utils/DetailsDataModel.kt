package com.movie.android.utils

import com.movie.android.domain.details.Details
import com.movie.android.domain.details.Recommendations
import com.movie.android.domain.details.Similarities

data class DetailsDataModel(
    private val similarities: Similarities,
    private val recommendations: Recommendations,
    private val details: Details
)