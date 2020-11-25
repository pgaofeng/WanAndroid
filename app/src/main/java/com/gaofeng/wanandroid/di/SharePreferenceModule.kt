package com.gaofeng.wanandroid.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 *  提供SharedPreference。所有的sp对象都应该通过该类获取
 */
@Module
@InstallIn(SingletonComponent::class)
object SharePreferenceModule {

    private const val SP_NAME = "sp_cookie"

    @Provides
    fun provideSp(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
    }
}