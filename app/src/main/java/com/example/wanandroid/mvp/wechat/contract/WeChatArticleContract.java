package com.example.wanandroid.mvp.wechat.contract;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.mvp.collect_base.CollectContract;
import com.example.wanandroid.network.ModelCallback;

/**
 * @author gaofengpeng
 * @date 2019/8/2
 * @description :
 */
public interface WeChatArticleContract {
    public interface View extends CollectContract.View {
        /**
         * 获取公众号对应的文章列表
         *
         * @param bean 文章列表数据
         */
        void getArticleListSuccess(ArticleBean bean);

        /**
         * 获取文章列表失败
         *
         * @param message 失败信息
         */
        void getArticleListFail(String message);
    }

    public interface Model {
        /**
         * 获取公众号列表文章
         *
         * @param id       公众号ID
         * @param page     页数
         * @param callback 回调接口
         */
        void getArticleList(int id, int page, ModelCallback callback);
    }

    public interface Presenter extends CollectContract.Presenter {
        /**
         * 获取公众号对应文章列表
         *
         * @param id   公众号id
         * @param page 文章页数
         */
        void getArticleList(int id, int page);
    }
}
