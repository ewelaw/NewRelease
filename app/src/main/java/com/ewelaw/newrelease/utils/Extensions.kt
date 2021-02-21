package com.ewelaw.newrelease.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.ewelaw.newrelease.R

fun ImageView.loadPictureWithGlide(pictureUrl: String) {
    Glide.with(this.context)
        .load(pictureUrl)
        .placeholder(R.drawable.ic_app)
        .into(this)
}
