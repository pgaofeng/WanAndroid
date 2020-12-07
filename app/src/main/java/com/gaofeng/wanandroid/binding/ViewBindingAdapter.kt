package com.gaofeng.wanandroid.binding

import android.view.View
import androidx.databinding.BindingAdapter

/**
 * 设置View的可见性，参数为boolean
 */
@BindingAdapter("visible")
fun bindVisible(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}