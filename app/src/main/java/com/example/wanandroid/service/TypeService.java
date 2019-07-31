package com.example.wanandroid.service;

import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.TypeBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author gaofengpeng
 * @date 2019/7/31
 * @description : 分类模块Service
 */
public interface TypeService {

    @GET("tree/json")
    Observable<BaseResponse<List<TypeBean>>> getTypeList();
}
