package com.gaofeng.wanandroid.network

import com.gaofeng.wanandroid.bean.Article
import com.gaofeng.wanandroid.bean.Banner
import com.gaofeng.wanandroid.bean.BaseBean
import com.gaofeng.wanandroid.bean.DataPaging
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *
 * @author 高峰
 * @date 2020/11/25 13:38
 * @desc 网络Api
 */
interface ServiceApi {

    /**
     * 获取置顶文章列表
     */
    @GET("article/top/json")
    suspend fun getHomeTopArticle(): BaseBean<List<Article>>

    /**
     * 获取Banner
     */
    @GET("banner/json")
    suspend fun getBanner(): BaseBean<List<Banner>>

    /**
     * 获取首页数据
     */
    @GET("article/list/{page}/json")
    suspend fun getHomeMainArticle(@Path("page") page: Int): BaseBean<DataPaging<Article>>

    /**
     * 获取广场数据
     */
    @GET("user_article/list/{page}/json")
    suspend fun getSquareArticle(@Path("page")page: Int):BaseBean<DataPaging<Article>>

    /**
     * 获取问答数据
     */
    @GET("wenda/list/{page}/json")
    suspend fun getAnswerArticle(@Path("page")page: Int):BaseBean<DataPaging<Article>>
}