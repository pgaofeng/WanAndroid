package com.gaofeng.wanandroid.ui.home

import com.gaofeng.wanandroid.network.ServiceApi
import javax.inject.Inject

/**
 *
 * @author 高峰
 * @date 2020/12/4 10:14
 * @desc 首页Repository，用于获取首页相关数据
 */
class HomeRepository @Inject constructor(private val api: ServiceApi) {
    /**
     * 获取首页置顶文章
     */
    suspend fun getHomeTopArticle() = api.getHomeTopArticle().resultData

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