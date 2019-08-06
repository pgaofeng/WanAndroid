package com.example.wanandroid.service;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.WeChatBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author WeChatService
 * @date 2019/8/1
 * ${DESCRIPTION}
 */
public interface WeChatService {
    /**
     * 获取微信列表
     *
     * @return 微信列表数据
     */
    @GET("wxarticle/chapters/json")
    Observable<BaseResponse<List<WeChatBean>>> getWxList();

    /**
     * 获取公众号文章列表
     *
     * @param id   公众号ID
     * @param page 页数
     * @return 文章列表数据
     */
    @GET("wxarticle/list/{id}/{page}/json")
    Observable<BaseResponse<ArticleBean>> getWxArticleList(@Path("id") int id, @Path("page") int page);
}
