package com.example.wanandroid.mvp.me.contract;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.network.ModelCallback;

/**
 * @author gaofengpeng
 * @date 2019/8/6
 * @description : 收藏Contract
 */
public interface CollectContract {
    public interface View {
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
    }

    public interface Model {
        /**
         * 获取文章列表
         *
         * @param page     分页页数
         * @param callback 回调
         */
        void getCollectList(int page, ModelCallback callback);
    }

    public interface Presenter {
        /**
         * 获取收藏文章列表
         *
         * @param page 分页页数
         */
        void getCollectList(int page);
    }
}
