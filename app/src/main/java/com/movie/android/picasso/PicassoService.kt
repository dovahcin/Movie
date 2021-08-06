package com.movie.android.picasso

import android.widget.ImageView
import com.movie.android.R
import com.squareup.picasso.Picasso

object PicassoService {

    fun initPicasso(uri: String, imageView: ImageView) {
        Picasso.get()
            .load(uri)
            .placeholder(R.drawable.placeholder)
            .into(imageView)
    }

}