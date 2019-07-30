package com.example.wanandroid.network;

import com.example.wanandroid.bean.BaseResponse;

/**
 * @author gaofengpeng
 * @date 2019/7/30
 * @description : Model的回调接口
 */
public interface ModelCallback {

    /**
     * 请求成功的回调
     *
     * @param baseData 返回值的基础bean
     */
    void success(BaseResponse<?> baseData);

    /**
     * @param throwable 异常信息
     */
    void fail(Throwable throwable);


}
