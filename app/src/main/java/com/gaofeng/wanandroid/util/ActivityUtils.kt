package com.gaofeng.wanandroid.util

import android.app.Activity

/**
 *
 * @author 高峰
 * @date 2020/12/4 17:02
 * @desc 该工具类主要用于收集Activity，用于管理
 */
object ActivityUtils {
    private val activities by lazy { mutableListOf<Activity>() }

    fun add(activity: Activity) = this.activities.add(activity)
    fun remove(activity: Activity) = activities.removeIf { it == activity }

    /**
     * 清空所有Activity并且结束掉
     */
    fun clear() {
        for (item in activities) item.finish()
        activities.clear()
    }

}