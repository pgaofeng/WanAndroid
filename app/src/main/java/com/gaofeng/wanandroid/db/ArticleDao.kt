package com.gaofeng.wanandroid.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gaofeng.wanandroid.bean.Article

/**
 * @author gaofeng
 * @date 2021/12/2
 *
 * 文章相关的Dao
 */
@Dao
abstract class ArticleDao {

    @Query("select * from Article")
    abstract fun queryAll(): PagingSource<Int, Article>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertAll(articles: List<Article>)

    @Query("delete from article")
    abstract suspend fun delete()
}