package com.gaofeng.wanandroid.di

import com.gaofeng.wanandroid.BuildConfig
import com.gaofeng.wanandroid.network.ServiceApi
import com.gaofeng.wanandroid.network.cookie.CookieJarImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * 提供网络相关对象，且都是单例对象
 */
@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {

    private const val BASE_URL = "https://www.wanandroid.com/"
    private const val TIMEOUT = 10L

    @Singleton
    @Provides
    fun provideOkHttpClient(cookieJar: CookieJarImpl): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .cookieJar(cookieJar)
            .callTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): ServiceApi {
        return retrofit.create(ServiceApi::class.java)
    }
}
