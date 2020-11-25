package com.gaofeng.wanandroid.network.cookie

import okhttp3.Cookie
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

/**
 *
 * @author 高峰
 * @date 2020/11/25 13:53
 * @desc Cookie载体，由于Cookie无法被序列化，因此这里通过PersistentCookie来间接将其序列化
 */
class PersistentCookie(@Transient private val cookie: Cookie) : Serializable {

    @Transient
    private lateinit var createCookie: Cookie

    /**
     * 获取对应的Cookie
     */
    fun getCookie(): Cookie {
        return createCookie
    }

    @Throws(IOException::class)
    private fun writeObject(outputStream: ObjectOutputStream) {
        //储存Cookie的信息
        outputStream.writeObject(cookie.name)
        outputStream.writeObject(cookie.value)
        outputStream.writeLong(cookie.expiresAt)
        outputStream.writeObject(cookie.domain)
        outputStream.writeObject(cookie.path)
        outputStream.writeBoolean(cookie.secure)
        outputStream.writeBoolean(cookie.httpOnly)
        outputStream.writeBoolean(cookie.hostOnly)
    }

    @Throws(IOException::class, ClassNotFoundException::class)
    private fun readObject(inputStream: ObjectInputStream) {
        val name = inputStream.readObject() as String
        val value = inputStream.readObject() as String
        val expiresAt = inputStream.readLong()
        val domain = inputStream.readObject() as String
        val path = inputStream.readObject() as String
        val secure = inputStream.readBoolean()
        val httpOnly = inputStream.readBoolean()
        val hostOnly = inputStream.readBoolean()

        //恢复时创建Cookie类
        var builder = Cookie.Builder()
            .name(name)
            .value(value)
            .expiresAt(expiresAt)
            .domain(domain)
            .path(path)
        builder = if (secure) builder.secure() else builder
        builder = if (httpOnly) builder.httpOnly() else builder
        builder = if (hostOnly) builder.hostOnlyDomain(domain) else builder.domain(domain)
        createCookie = builder.build()
    }
}