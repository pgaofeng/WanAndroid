package com.gaofeng.wanandroid.ui.home.fragment

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.gaofeng.wanandroid.bean.Article
import com.gaofeng.wanandroid.common.CommonDataSource
import com.gaofeng.wanandroid.network.ServiceApi
import javax.inject.Inject

/**
 * @author gaofeng
 * @date 2021/12/3
 */
class ArticleRepository @Inject constructor(
    private val remoteDataSource: RemoteArticleDataSource
) {
    /**
     * 获取文章分页数据
     */
    fun getArticlePaging(type: Int): Pager<Int, Article> {
        return Pager(
            PagingConfig(pageSize = 20, prefetchDistance = 10),
        ) {
            CommonDataSource { page ->
                val data = when (type) {
                    0 -> remoteDataSource.getHomeMainArticle(page)
                    1 -> remoteDataSource.getSquareArticle(page)
                    2 -> remoteDataSource.getAnswerArticle(page)
                    else -> remoteDataSource.getAnswerArticle(page)
                }
                data
            }
        }
    }
}

class RemoteArticleDataSource @Inject constructor(
    private val api: ServiceApi
) {

    /**
     * 获取首页分页数据
     */
    suspend fun getHomeMainArticle(page: Int) = api.getHomeMainArticle(page).resultData

    /**
     * 获取广场数据
     */
    suspend fun getSquareArticle(page: Int) = api.getSquareArticle(page).resultData

    /**
     * 获取问答数据
     */
    suspend fun getAnswerArticle(page: Int) = api.getAnswerArticle(page).resultData
}