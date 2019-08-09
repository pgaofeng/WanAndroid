package com.example.wanandroid.service;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.TypeBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author gaofengpeng
 * @date 2019/7/31
 * @description : 分类模块Service
 */
public interface TypeService {

    /**
     * 获取分类数据
     * @return 分类数据
     */
    @GET("tree/json")
    Observable<BaseResponse<List<TypeBean>>> getTypeList();

    /**
     * 获取分类下的文章
     * @param page 页数
     * @param cid 分类id
     * @return 分类文章
     */
    @GET("article/list/{page}/json")
    Observable<BaseResponse<ArticleBean>> getTypeArticle(@Path("page") int page, @Query("cid")int cid);
}
