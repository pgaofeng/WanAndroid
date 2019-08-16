package com.example.wanandroid.network;

import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.mvp.login.LoginException;
import com.pgaofeng.common.network.DisposableManager;

import io.reactivex.observers.DisposableObserver;

/**
 * @author gaofengpeng
 * @date 2019/8/16
 * @description :
 */
public abstract class BaseObserver<T extends BaseResponse> extends DisposableObserver<T> {

    protected BaseObserver(DisposableManager disposableManager) {
        disposableManager.addDisposable(this);
    }

    @Override
    public void onNext(T t) {
        // 未登录
        if (t.getErrorCode() == -1001) {
            onError(new LoginException("请先登录"));
        } else if (t.getErrorCode() == 0) {
            onSuccess(t);
        } else {
            onError(new Throwable("未知错误"));
        }
    }

    @Override
    public void onError(Throwable e) {
        onFail(e);
    }

    @Override
    public void onComplete() {
    }

    /**
     * 数据成功返回的回调
     *
     * @param t 获取到的数据
     */
    public abstract void onSuccess(T t);

    /**
     * 请求数据出现错误的回调
     *
     * @param throwable 异常信息
     */
    public abstract void onFail(Throwable throwable);
}