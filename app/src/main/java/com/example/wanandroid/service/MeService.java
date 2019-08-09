package com.example.wanandroid.service;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.CollectWebsiteBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

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
}
