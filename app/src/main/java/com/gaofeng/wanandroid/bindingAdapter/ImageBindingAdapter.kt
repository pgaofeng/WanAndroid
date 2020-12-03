package com.gaofeng.wanandroid.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.gaofeng.wanandroid.util.ImageUtils

/**
 *
 * @author 高峰
 * @date 2020/12/3 15:18
 * @desc 图片相关的Binding
 */

@BindingAdapter("url")
fun bindImage(imageView: ImageView, url: String) {
    ImageUtils.loadImage(imageView.context, imageView, url)
}