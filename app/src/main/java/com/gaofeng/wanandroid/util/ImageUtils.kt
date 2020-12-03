package com.gaofeng.wanandroid.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageUtils {

    fun loadImage(context: Context, imageView: ImageView, url: String) {
        Glide.with(context).load(url).into(imageView)
    }

}