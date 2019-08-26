package com.example.wanandroid.service;

import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author LoginService
 * @date 2019/8/2
 * ${DESCRIPTION}
 */
public interface LoginService {
    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录信息
     */
    @FormUrlEncoded
    @POST("/user/login")
    Observable<BaseResponse<LoginBean>> login(@Field("username") String username, @Field("password") String password);

    @GET("/user/logout/json")
    Observable<BaseResponse> logout();
}
