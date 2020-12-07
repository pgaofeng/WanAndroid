package com.gaofeng.wanandroid.bean

/**
 * 数据分页信息
 */
data class DataPaging<T>(
    val curPage: Int = 0,
    val datas: List<T> = emptyList(),
    val offset: Int = 0,
    val over: Boolean = false,
    val pageCount: Int = 0,
    val size: Int = 0,
    val total: Int = 0
)