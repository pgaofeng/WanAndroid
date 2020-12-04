package com.gaofeng.wanandroid.network

import com.gaofeng.wanandroid.bean.ArticleBean
import com.gaofeng.wanandroid.bean.BaseBean
import retrofit2.http.GET

/**
 *
 * @author 高峰
 * @date 2020/11/25 13:38
 * @desc 网络Api
 */
interface ServiceApi {

    @GET("article/top/json")
    suspend fun getHomeTopArticle(): BaseBean<List<ArticleBean>>

}