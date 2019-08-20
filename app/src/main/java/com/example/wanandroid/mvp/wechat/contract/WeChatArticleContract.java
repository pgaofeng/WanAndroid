package com.example.wanandroid.mvp.wechat.contract;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.DatasBean;
import com.example.wanandroid.mvp.collect_base.CollectContract;
import com.example.wanandroid.network.ModelCallback;

import java.util.List;

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

        /**
         * 成功从缓存中获取到数据
         *
         * @param datasBeans 缓存数据
         */
        void getArticleCacheSuccess(List<DatasBean> datasBeans);
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

        /**
         * 从缓存中获取文章数据
         *
         * @param id       id
         * @param callback 回调接口
         */
        void getArticleCache(int id, ModelCallback callback);
    }

    public interface Presenter extends CollectContract.Presenter {
        /**
         * 获取公众号对应文章列表
         *
         * @param id   公众号id
         * @param page 文章页数
         */
        void getArticleList(int id, int page);

        /**
         * 从缓存中获取公众号文章
         *
         * @param id id
         */
        void getArticleListCache(int id);
    }
}
