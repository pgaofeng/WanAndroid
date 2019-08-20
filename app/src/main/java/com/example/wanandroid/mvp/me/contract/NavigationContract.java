package com.example.wanandroid.mvp.me.contract;

import com.example.wanandroid.bean.HeaderBean;
import com.example.wanandroid.network.ModelCallback;

import java.util.List;

/**
 * @author gaofengpeng
 * @date 2019/8/12
 * @description :
 */
public interface NavigationContract {
    public interface View {
        /**
         * 获取导航数据成功
         *
         * @param data 导航数据
         */
        void getNavigationSuccess(List<HeaderBean> data);

        /**
         * 获取导航数据失败
         *
         * @param message 失败信息
         */
        void getNavigationFail(String message);
    }

    public interface Model {
        /**
         * 获取导航数据
         *
         * @param callback 回调接口
         */
        void getNavigation(ModelCallback callback);

        /**
         * 从缓存中获取数据
         *
         * @param callback 回调接口
         */
        void getNaviCache(ModelCallback callback);
    }

    public interface Presenter {
        /**
         * 获取导航数据
         */
        void gatNavigation();

        /**
         * 从缓存中获取导航数据
         */
        void getNaviCache();
    }


}
