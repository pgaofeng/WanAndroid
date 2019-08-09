package com.example.wanandroid.mvp.me.contract;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.CollectWebsiteBean;
import com.example.wanandroid.network.ModelCallback;
import com.pgaofeng.common.mvp.View;

import java.util.List;

/**
 * @author gaofengpeng
 * @date 2019/8/6
 * @description : 收藏Contract
 */
public interface CollectContract {
    public interface View extends com.pgaofeng.common.mvp.View {
        /**
         * 获取收藏文章列表成功
         *
         * @param bean 文章列表数据
         */
        void getCollectListSuccess(ArticleBean bean);

        /**
         * 获取收藏文章列表失败
         *
         * @param message 失败信息
         */
        void getCollectFail(String message);

        /**
         * 获取收藏网址列表成功
         *
         * @param list 网址列表
         */
        void getCollectWebsiteSuccess(List<CollectWebsiteBean> list);

        /**
         * 获取收藏网址列表失败
         *
         * @param message 失败原因
         */
        void getCollectWebsiteFail(String message);
    }

    public interface Model {
        /**
         * 获取文章列表
         *
         * @param page     分页页数
         * @param callback 回调
         */
        void getCollectList(int page, ModelCallback callback);

        /**
         * 获取收藏网址列表
         *
         * @param callback 回调接口
         */
        void getCollectWebsite(ModelCallback callback);
    }

    public interface Presenter {
        /**
         * 获取收藏文章列表
         *
         * @param page 分页页数
         */
        void getCollectList(int page);

        /**
         * 获取收藏网址列表
         */
        void getCollectWebsite();
    }
}
