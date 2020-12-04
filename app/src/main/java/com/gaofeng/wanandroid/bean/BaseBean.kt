package com.gaofeng.wanandroid.bean

import com.gaofeng.wanandroid.exception.UserApiException

/**
 * @author 高峰
 * @date 2020/12/4
 * 网络BaseBean
 */
data class BaseBean<T : Any>(
    val errorCode: Int = 0,
    val errorMsg: String = "",
    private val data: T?
) {
    val resultData: T
        get() = if (errorCode != 0 || data == null)
            throw UserApiException(errorCode, errorMsg)
        else
            data
}
