package com.movie.android.picasso

import android.widget.ImageView
import com.movie.android.R
import com.squareup.picasso.Picasso
import java.net.URI


    fun ImageView.loadImage(uri: String) {
        Picasso.get()
            .load(uri)
            .placeholder(R.drawable.ic_placeholder)
            .into(this)
    }