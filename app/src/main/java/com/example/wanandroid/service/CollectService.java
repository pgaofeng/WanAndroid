package com.example.wanandroid.service;

import com.example.wanandroid.bean.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author gaofengpeng
 * @date 2019/8/9
 * @description : 收藏相关Service
 */
public interface CollectService {

    /**
     * 收藏站内文章
     *
     * @param articleId 文章ID
     */
    @POST("lg/collect/{articleId}/json")
    Observable<BaseResponse> collectInsideArticle(@Path("articleId") int articleId);

    /**
     * 收藏站外的文章
     *
     * @param author 作者
     * @param title  标题
     * @param link   连接
     */
    @FormUrlEncoded
    @POST("lg/collect/add/json")
    Observable<BaseResponse> collectOutsideArticle(@Field("author") String author, @Field("title") String title, @Field("link") String link);

    /**
     * 取消收藏文章
     *
     * @param articleId 文章ID
     */
    @POST("lg/uncollect_originId/{articleId}/json")
    Observable<BaseResponse> unCollectArticle(@Path("articleId") int articleId);

    /**
     * 收藏界面取消收藏
     *
     * @param articleId 收藏界面的文章ID
     * @param originId  所属分类ID
     */
    @FormUrlEncoded
    @POST("lg/uncollect/{articleId}/json")
    Observable<BaseResponse> unCollectArticleFromCollect(@Path("articleId") int articleId, @Field("originId") int originId);


}
