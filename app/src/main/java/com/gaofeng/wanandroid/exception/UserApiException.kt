package com.gaofeng.wanandroid.exception

/**
 *
 * @author 高峰
 * @date 2020/12/4 10:27
 * @desc 用户异常，服务器正常返回数据的errorCode不为0的时候，将会手动抛出该异常
 */
class UserApiException(val code: Int, message: String) : RuntimeException(message)