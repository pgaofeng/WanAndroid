package com.gaofeng.wanandroid.screen.home.repository

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
     * 获取Banner数据
     */
    suspend fun getBanner() = api.getBanner().resultData

    /**
     * 获取首页分页数据
     */
    suspend fun getHomeMainArticle(page: Int) = api.getHomeMainArticle(page).resultData
}