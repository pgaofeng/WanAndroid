package com.gaofeng.wanandroid.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * @author 高峰
 * @date 2020/12/4
 *
 * 关于图片加载相关的工具类
 */
object ImageUtils {

    fun loadImage(context: Context, imageView: ImageView, url: String) {
        Glide.with(context).load(url).into(imageView)
    }

}