package com.movie.android.utils

import android.content.res.Resources
import android.view.View
import android.widget.ImageView
import com.movie.android.R
import com.squareup.picasso.Picasso

fun screenWidth() = Resources.getSystem().displayMetrics.widthPixels

fun ImageView.loadImage(uri: String) {
  Picasso.get()
    .load(uri)
    .placeholder(R.drawable.ic_placeholder)
    .into(this)
}

fun View.visible(isVisible: Boolean) {
  visibility = if (isVisible) View.VISIBLE else View.GONE
}