package com.gaofeng.wanandroid.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gaofeng.wanandroid.bean.Article

/**
 * @author gaofeng
 * @date 2021/12/2
 *
 * 数据库，用于存放文章列表相关
 */
@Database(
    version = 1,
    entities = [Article::class]
)
abstract class WanDatabase :RoomDatabase(){

    abstract fun articleDao():ArticleDao
}