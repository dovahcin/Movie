package com.movie.android.movielist.utils

import android.content.res.Resources
import android.view.View
import android.widget.ImageView
import androidx.annotation.FloatRange
import com.movie.android.movielist.R
import com.squareup.picasso.Picasso

fun screenWidth() = Resources.getSystem().displayMetrics.widthPixels

fun ImageView.loadImage(uri: String) {
  Picasso.get()
    .load(uri)
    .placeholder(R.drawable.ic_placeholder)
    .into(this)
}

infix fun View.reduceWidthBy(@FloatRange(from = 0.0, to = 1.0) width: Float) {
  layoutParams.width = ((screenWidth() * width).toInt())
}

fun View.visible(isVisible: Boolean) {
  visibility = if (isVisible) View.VISIBLE else View.GONE
}

