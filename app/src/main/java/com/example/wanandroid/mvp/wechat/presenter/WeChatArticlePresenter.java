package com.example.wanandroid.mvp.wechat.presenter;

import android.view.View;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.mvp.collect_base.CollectModel;
import com.example.wanandroid.mvp.login.LoginException;
import com.example.wanandroid.mvp.login.view.LoginActivity;
import com.example.wanandroid.mvp.wechat.contract.WeChatArticleContract;
import com.example.wanandroid.mvp.wechat.model.WeChatModel;
import com.example.wanandroid.mvp.wechat.view.WeCharArticleFragment;
import com.example.wanandroid.network.ModelCallback;
import com.pgaofeng.common.base.BasePresenter;

import java.util.List;

/**
 * @author gaofengpeng
 * @date 2019/8/2
 * @description :
 */
public class WeChatArticlePresenter extends BasePresenter<WeCharArticleFragment, WeChatModel> implements WeChatArticleContract.Presenter {
    public WeChatArticlePresenter(WeCharArticleFragment view) {
        super(view);
    }

    @Override
    public void getArticleList(int id, int page) {
        mModel.getArticleList(id, page, new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.getArticleListSuccess((ArticleBean) baseData.getData());
            }

            @Override
            public void fail(Throwable throwable) {
                mView.getArticleListFail(throwable.getMessage());
            }
        });
    }

    @Override
    public void collectInside(int position, View view, int articleId) {
        CollectModel.collectInside(articleId, mModel.getDisposableManager(), new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.collectSuccess(position, view);
            }

            @Override
            public void fail(Throwable throwable) {
                if (throwable instanceof LoginException) {
                    mView.toLogin(LoginActivity.class);
                }
                mView.collectFail(position, view);
            }
        });
    }

    @Override
    public void collectOutside(int position, View view,String author, String title, String link) {

    }

    @Override
    public void unCollect(int position, View view,int articleId) {
        CollectModel.unCollectArticle(articleId, mModel.getDisposableManager(), new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.unCollectSuccess(position, view);
            }

            @Override
            public void fail(Throwable throwable) {
                if (throwable instanceof LoginException) {
                    mView.toLogin(LoginActivity.class);
                }
                mView.unCollectFail(position, view);
            }
        });
    }

    @Override
    public void unCollectFromCollect(int position, View view,int articleId, int originId) {

    }

    @Override
    protected WeChatModel createModel() {
        return new WeChatModel();
    }
}
