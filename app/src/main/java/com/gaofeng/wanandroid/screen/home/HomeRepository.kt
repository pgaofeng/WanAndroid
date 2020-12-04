package com.gaofeng.wanandroid.screen.home

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
}