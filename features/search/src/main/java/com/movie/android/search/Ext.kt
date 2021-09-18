package com.movie.android.search

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImage(uri: String) {
  Picasso.get()
    .load(uri)
    .placeholder(R.drawable.ic_placeholder)
    .into(this)
}