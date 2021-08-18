package com.gaofeng.wanandroid.ext

import android.view.View

/**
 *
 * @author 高峰
 * @date 2020/12/8 13:55
 * @desc View的拓展方法和属性
 */

// 设置View显示Visibility
fun View.visible() {
    visibility = View.VISIBLE
}

// 设置View显示GONE
fun View.gone() {
    visibility = View.GONE
}