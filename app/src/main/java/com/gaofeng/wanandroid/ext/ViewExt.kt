package com.gaofeng.wanandroid.ext

import android.view.View

/**
 *
 * @author 高峰
 * @date 2020/12/8 13:55
 * @desc View的拓展方法和属性
 */

// 设置View是否是Visibility
var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }