package com.gaofeng.wanandroid.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaofeng.wanandroid.exception.UserApiException
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.ConnectException

/**
 *
 * @author 高峰
 * @date 2020/11/25 16:12
 * @desc ViewModel 基类
 */
open class BaseViewModel : ViewModel() {

    fun launch(block: suspend () -> Unit) {

        viewModelScope.launch {
            try {
                block()
            } catch (exception: Exception) {
                when (exception) {
                    is UserApiException -> {
                        // 网络请求成功，但是返回的是错误信息
                        println("数据返回出错：${exception.message}")
                    }
                    is CancellationException -> {
                        // 协程取消导致的异常
                        println("协程取消网络请求异常")
                    }
                    is JsonDataException -> {
                        // Json转换出错
                        println("数据格式转换错误")
                    }
                    is ConnectException, is HttpException -> {
                        // 网络出现异常
                        println("网络出现了异常")
                    }
                    else -> {
                        // 其他错误
                        println("其他错误")
                    }
                }
                exception.printStackTrace()
            }
        }
    }

    fun <T> async(block: suspend () -> T) = viewModelScope.async {
        block()
    }


}