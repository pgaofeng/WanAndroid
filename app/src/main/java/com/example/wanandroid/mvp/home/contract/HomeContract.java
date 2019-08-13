package com.example.wanandroid.mvp.home.contract;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.mvp.collect_base.CollectContract;
import com.example.wanandroid.network.ModelCallback;

import java.util.List;

/**
 * @author gaofengpeng
 * @date 2019/7/30
 * @description : 首页Contract
 */
public interface HomeContract {
    interface View extends CollectContract.View {
        /**
         * 获取文章列表成功
         *
         * @param bean 文章数据
         */
        void getArticleSuccess(ArticleBean bean);

        /**
         * 获取文章列表失败
         *
         * @param message 失败信息
         */
        void getArticleFail(String message);

        /**
         * 获取首页置顶文章
         *
         * @param bean 文章数据
         */
        void getTopArticleListSuccess(List<ArticleBean.DatasBean> bean);

        /**
         * 获取置顶文章失败
         *
         * @param message 失败原因
         */
        void getTopArticleListFail(String message);

        /**
         * 开始更新软件
         */
        void startUpdate();

        /**
         * 更新下载进度
         *
         * @param cur   当前下载
         * @param total 总大小
         */
        void updateProgress(long cur, long total);

        /**
         * 更新成功
         */
        void updateSuccess();

        /**
         * 更新失败
         *
         * @param message 失败原因
         */
        void updateFail(String message);

    }

    interface Presenter extends CollectContract.Presenter {
        /**
         * 获取文章列表
         *
         * @param page 文章所在页数
         */
        void getArticleList(int page);

        /**
         * 获取置顶文章
         */
        void getTopArticleList();

        /**
         * 更新软件
         */
        void update();
    }

    interface Model {
        /**
         * 获取文章列表
         *
         * @param page     页数
         * @param callBack 回调接口
         */
        void getArticleList(int page, ModelCallback callBack);

        /**
         * 获取置顶文章
         *
         * @param callback 回调接口
         */
        void getTopArticleList(ModelCallback callback);

        /**
         * 更新软件
         *
         * @param callback 回调接口
         */
        void update(DownLoadListener callback);
    }

    public interface DownLoadListener {
        /**
         * 开始下载
         */
        void startDownload();

        /**
         * 下载进度
         *
         * @param cur   当前下载大小
         * @param total 所有大小
         */
        void downLoadProgress(long cur, long total);

        /**
         * 下载成功
         */
        void downLoadSuccess();

        /**
         * 下载失败
         *
         * @param message 失败信息
         */
        void downLoadFail(String message);
    }
}
