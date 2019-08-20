package com.example.wanandroid.mvp.me.contract;

import com.example.wanandroid.bean.CollectWebsiteBean;
import com.example.wanandroid.network.ModelCallback;

import java.util.List;

/**
 * @author gaofengpeng
 * @date 2019/8/9
 * @description :
 */
public interface CommonlyContract {
    public interface View {
        /**
         * 获取常用网站成功
         *
         * @param data 常用网站列表
         */
        void getCommonlyWebsiteSuccess(List<CollectWebsiteBean> data);

        /**
         * 获取常用网站列表失败
         *
         * @param message 失败信息
         */
        void getCommonlyWebsiteFail(String message);
    }

    public interface Model {
        /**
         * 获取常用网站
         *
         * @param callback 回调接口
         */
        void getCommonlyWebsite(ModelCallback callback);

        /**
         * 从缓存中获取常用网站
         *
         * @param callback 回调接口
         */
        void getCommonlyCache(ModelCallback callback);
    }

    public interface Presenter {
        /**
         * 获取常用网站
         */
        void getCommonlyWebsite();

        /**
         * 从缓存中获取常用网站
         */
        void getCommonlyCache();
    }
}
