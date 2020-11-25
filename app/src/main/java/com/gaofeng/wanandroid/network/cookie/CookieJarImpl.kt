package com.gaofeng.wanandroid.network.cookie

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 * @author 高峰
 * @date 2020/11/25 13:55
 * @desc 用于保存网络请求的Cookie，使用Cookie登录
 */
@Singleton
class CookieJarImpl @Inject constructor() : CookieJar {

    @Inject
    lateinit var cookieStore: CookieStore

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return cookieStore.getCookie(url.host)
    }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        for (cookie in cookies) {
            cookieStore.saveCookie(url.host, cookie)
        }
    }
}