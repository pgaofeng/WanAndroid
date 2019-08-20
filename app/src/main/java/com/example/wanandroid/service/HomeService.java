package com.example.wanandroid.service;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.DatasBean;
import com.example.wanandroid.bean.HotKeyBean;
import com.example.wanandroid.bean.UpdateBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

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

    /**
     * 获取首页文章
     *
     * @return 首页文章数据
     */
    @GET("article/top/json")
    Observable<BaseResponse<List<DatasBean>>> topArticleList();

    /**
     * 获取搜索热词
     *
     * @return 热词数据
     */
    @GET("hotkey/json")
    Observable<BaseResponse<List<HotKeyBean>>> getHotKey();

    /**
     * 搜索
     *
     * @param page 页码数
     * @param key  关键字
     * @return 搜索结果
     */
    @FormUrlEncoded
    @POST("article/query/{page}/json")
    Observable<BaseResponse<ArticleBean>> search(@Path("page") int page, @Field("k") String key);

    /**
     * 下载更新
     *
     * @param url 下载地址
     * @return Stream
     */
    @Streaming
    @GET
    Call<ResponseBody> update(@Url String url);

    /**
     * 检查更新
     *
     * @param url 更新地址
     * @return 更新结果
     */
    @GET
    Observable<List<UpdateBean>> checkUpdate(@Url String url);
}
