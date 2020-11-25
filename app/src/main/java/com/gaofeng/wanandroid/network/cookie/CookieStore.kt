package com.gaofeng.wanandroid.network.cookie

import android.content.SharedPreferences
import androidx.core.content.edit
import okhttp3.Cookie
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.nio.charset.Charset
import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 * @author 高峰
 * @date 2020/11/25 14:25
 *
 * CookieStore，负责存储Cookie实例。在该对象中，将Cookie序列化成String，然后存储在SP中，同时在内存中也存储一份
 *保存在Map中。其中key为host@cookieName
 */
@Singleton
class CookieStore @Inject constructor(private val preferences: SharedPreferences) {

    private val cookies: MutableMap<String, Cookie> by lazy { mutableMapOf() }

    init {
        for ((key, value) in preferences.all) {
            cookies[key] = decodeCookie(value as String)
        }
    }

    /**
     * 将字符串转换成PersistentCookie，进而获取到实际Cookie
     */
    private fun decodeCookie(cookieString: String): Cookie {
        ObjectInputStream(ByteArrayInputStream(cookieString.toByteArray(Charset.forName(CHARSET)))).use {
            return (it.readObject() as PersistentCookie).getCookie()
        }
    }

    /**
     * 将Cookie转换成PersistentCookie，进而将其写入文件，然后再读成字符串
     */
    private fun decodeCookieString(cookie: Cookie): String {
        val output = ByteArrayOutputStream()
        ObjectOutputStream(output).use {
            it.writeObject(PersistentCookie(cookie))
        }
        output.use {
            return String(it.toByteArray(), Charset.forName(CHARSET))
        }
    }

    /**
     * 将Cookie保存到本地和内存中去
     */
    fun saveCookie(url: String, cookie: Cookie) {
        val cookieString = decodeCookieString(cookie)
        val cookieKey = "$url@${cookie.name}"
        preferences.edit {
            putString(cookieKey, cookieString)
        }
        cookies[cookieKey] = cookie
    }

    /**
     * 获取相应的Cookie
     */
    fun getCookie(url: String) = cookies.filter { it.key.contains(url) }
        .map { it.value }
        .toList()


    companion object {
        const val CHARSET = "ISO-8859-1"
    }
}