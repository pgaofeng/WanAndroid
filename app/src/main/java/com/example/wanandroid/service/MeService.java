package com.example.wanandroid.service;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BaseResponse;

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
}
