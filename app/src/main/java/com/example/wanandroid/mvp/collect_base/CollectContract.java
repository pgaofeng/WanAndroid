package com.example.wanandroid.mvp.collect_base;

import android.view.View;

/**
 * @author gaofengpeng
 * @date 2019/8/9
 * @description :
 */
public interface CollectContract {
    public interface View {

        /**
         * 收藏成功
         */
        void collectSuccess(int position, android.view.View view);

        /**
         * 收藏失败
         */
        void collectFail(int position,android.view.View view);

        /**
         * 取消收藏成功
         */
        void unCollectSuccess(int position,android.view.View view);

        /**
         * 取消收藏失败
         */
        void unCollectFail(int position,android.view.View view);
    }

    public interface Presenter {
        /**
         * 收藏站内文章
         *
         * @param articleId 文章ID
         */
        void collectInside(int position,android.view.View view,int articleId);

        /**
         * 收藏站外文章
         *
         * @param author 作者
         * @param link   连接
         * @param title  标题
         */
        void collectOutside(int position,android.view.View view,String author, String title, String link);

        /**
         * 取消收藏
         *
         * @param articleId 文章ID
         */
        void unCollect(int position,android.view.View view,int articleId);

        /**
         * 从收藏界面取消收藏
         *
         * @param articleId 收藏下的文章ID
         * @param originId  文章所在分类ID
         */

        void unCollectFromCollect(int position,android.view.View view,int articleId, int originId);
    }
}
