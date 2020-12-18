package com.gaofeng.wanandroid.binding

import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter

/**
 * 设置View的可见性，参数为boolean
 */
@BindingAdapter("visible")
fun bindVisible(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("html")
fun bindHtml(view: TextView, text: String?) {
    text ?: return
    view.text = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
}

@BindingAdapter("src")
fun bindSrc(view: ImageView, @DrawableRes resId: Int) {
    view.setImageResource(resId)
}