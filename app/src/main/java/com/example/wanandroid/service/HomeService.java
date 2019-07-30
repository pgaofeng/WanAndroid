package com.example.wanandroid.service;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author gaofengpeng
 * @date 2019/7/30
 * @description : 首页的Service，提供网络接口
 */
public interface HomeService {

    /**
     * 首页获取文章列表
     *
     * @param page 分页获取的页数
     * @return 返回数据
     */
    @GET("article/list/{page}/json")
    Observable<BaseResponse<ArticleBean>> articleList(@Path("page") int page);
}
