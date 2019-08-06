package com.example.wanandroid.mvp.home.contract;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.HotKeyBean;
import com.example.wanandroid.network.ModelCallback;

import java.util.List;

/**
 * @author gaofengpeng
 * @date 2019/8/6
 * @description :搜索界面契约类
 */
public interface SearchContract {
    public interface View {

        /**
         * 获取热词成功
         *
         * @param list 热词列表
         */
        void getHotKeySuccess(List<HotKeyBean> list);

        /**
         * 获取热词失败
         *
         * @param message 失败原因
         */
        void getHotKeyFail(String message);

        /**
         * 搜索成功
         *
         * @param bean 搜索结果集
         */
        void searchSuccess(ArticleBean bean);

        /**
         * 搜索失败
         *
         * @param message 失败愿意
         */
        void searchFail(String message);

        /**
         * 获取搜索历史成功
         *
         * @param list 历史搜索列表
         */
        void getHistorySuccess(List<String> list);

        /**
         * 获取历史搜索记录失败
         *
         * @param message 失败原因
         */
        void getHistoryFail(String message);
    }

    public interface Model {
        /**
         * 获取搜索热词
         *
         * @param callback 回调接口
         */
        void getHotKey(ModelCallback callback);

        /**
         * 获取搜索历史记录
         *
         * @param callback 回调接口
         */
        void getHistory(ModelCallback callback);

        /**
         * 搜索文章
         *
         * @param page     页码数
         * @param key      关键字
         * @param callback 回调接口
         */
        void searchArticle(int page, String key, ModelCallback callback);
    }

    public interface Presenter {
        /**
         * 获取搜索热词
         */
        void getHotKey();

        /**
         * 获取搜索历史
         */
        void getHistory();

        /**
         * 搜索文章
         *
         * @param page 页码
         * @param key  关键词
         */
        void searchArticle(int page, String key);
    }
}
