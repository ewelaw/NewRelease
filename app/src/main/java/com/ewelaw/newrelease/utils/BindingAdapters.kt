package com.ewelaw.newrelease.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ewelaw.newrelease.R

@BindingAdapter("image")
fun bindImage(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .placeholder(R.drawable.ic_app)
        .into(imageView)
}

@BindingAdapter("timeMin")
fun bindDurationMsToMin(textView: TextView, duration: Int) {
    textView.text = String.format("%.2f", duration / 60000.0) + "min"
}