package com.example.wanandroid.service;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BasePageBean;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.CollectWebsiteBean;
import com.example.wanandroid.bean.NavigationBean;
import com.example.wanandroid.bean.TodoBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author gaofengpeng
 * @date 2019/8/6
 * @description :我的界面相关Service
 */
public interface MeService {
    /**
     * 获取收藏文章数据
     *
     * @param page 分页页数
     * @return 收藏文章数据
     */
    @GET("lg/collect/list/{page}/json")
    Observable<BaseResponse<ArticleBean>> getCollectList(@Path("page") int page);


    /**
     * 获取收藏的网址
     *
     * @return 收藏网址数据
     */
    @GET("lg/collect/usertools/json")
    Observable<BaseResponse<List<CollectWebsiteBean>>> getCollectWebsite();

    /**
     * 获取常用网站列表
     *
     * @return 常用网站集合
     */
    @GET("friend/json")
    Observable<BaseResponse<List<CollectWebsiteBean>>> getCommonlyWebsite();

    /**
     * 获取Todo列表，orderby 1:完成日期顺序；2.完成日期逆序；3.创建日期顺序；4.创建日期逆序(默认)；
     *
     * @param page   页数
     * @param status 0未完成，1已完成
     * @return Todo列表信息
     */
    @GET("lg/todo/v2/list/{page}/json?orderby=4")
    Observable<BaseResponse<BasePageBean<List<TodoBean>>>> getTodoList(@Path("page") int page, @Query("status") int status);

    /**
     * 获取导航数据
     *
     * @return 导航数据
     */
    @GET("navi/json")
    Observable<BaseResponse<List<NavigationBean>>> getNavigation();


}
