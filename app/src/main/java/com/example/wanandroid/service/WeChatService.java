package com.example.wanandroid.service;

import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.WeChatBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

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
}
