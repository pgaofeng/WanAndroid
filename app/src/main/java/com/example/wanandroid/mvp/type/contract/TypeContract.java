package com.example.wanandroid.mvp.type.contract;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.TypeBean;
import com.example.wanandroid.mvp.collect_base.CollectContract;
import com.example.wanandroid.network.ModelCallback;
import com.pgaofeng.common.mvp.View;

import java.util.List;

/**
 * @author gaofengpeng
 * @date 2019/7/31
 * @description : 分类模块契约类
 */
public class TypeContract {

    public interface View extends com.pgaofeng.common.mvp.View , CollectContract.View {
        /**
         * 获取分类数据成功
         *
         * @param datas 分类数据
         */
        void getTypeListSuccess(List<TypeBean> datas);

        /**
         * 获取分类数据失败
         *
         * @param message 失败信息
         */
        void getTypeListFail(String message);

        /**
         * 获取分类下的文章成功
         *
         * @param bean 文章数据
         */
        void getTypeArticleSuccess(ArticleBean bean);

        /**
         * 获取分类下的文章失败
         *
         * @param message 失败原因
         */
        void getTypeArticleFail(String message);

    }

    public interface Presenter extends CollectContract.Presenter{
        /**
         * 获取分类数据
         */
        void getTypeList();

        /**
         * 获取分类下的文章
         *
         * @param page 页数
         * @param cid  分类id
         */
        void getTypeArticle(int page, int cid);
    }

    public interface Model {
        /**
         * 获取分类数据
         *
         * @param callback 回调接口
         */
        void getTypeList(ModelCallback callback);

        /**
         * 获取分类下的文章
         *
         * @param page     页数
         * @param cid      分类id
         * @param callback 回调接口
         */
        void getTypeArticle(int page, int cid, ModelCallback callback);
    }

}
