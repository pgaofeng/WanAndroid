package com.gaofeng.wanandroid.di

import android.content.Context
import androidx.room.Room
import com.gaofeng.wanandroid.db.ArticleDao
import com.gaofeng.wanandroid.db.WanDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author gaofengpeng
 * @date 2021/12/2
 */
@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    private const val DBName = "room_article_wan"

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): WanDatabase {
        return Room.databaseBuilder(context, WanDatabase::class.java, DBName)
            .build()
    }

    @Singleton
    @Provides
    fun provideArticleDao(db: WanDatabase): ArticleDao {
        return db.articleDao()
    }
}