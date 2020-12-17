package com.gaofeng.wanandroid.bean

/**
 *
 * @author 高峰
 * @date 2020/12/17 17:47
 * @desc 提供给Adapter的Type
 */
interface AdapterType {
    fun itemType(position: Int): Int
}