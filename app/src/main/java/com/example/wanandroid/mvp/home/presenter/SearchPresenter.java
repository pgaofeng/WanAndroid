package com.example.wanandroid.mvp.home.presenter;

import android.view.View;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.HotKeyBean;
import com.example.wanandroid.mvp.collect_base.CollectModel;
import com.example.wanandroid.mvp.home.model.SearchModel;
import com.example.wanandroid.mvp.home.contract.SearchContract;
import com.example.wanandroid.mvp.home.view.SearchActivity;
import com.example.wanandroid.mvp.login.LoginException;
import com.example.wanandroid.mvp.login.view.LoginActivity;
import com.example.wanandroid.network.ModelCallback;
import com.pgaofeng.common.base.BasePresenter;

import java.util.List;

/**
 * @author gaofengpeng
 * @date 2019/8/6
 * @description :
 */
public class SearchPresenter extends BasePresenter<SearchActivity, SearchModel> implements SearchContract.Presenter {
    public SearchPresenter(SearchActivity view) {
        super(view);
    }

    @Override
    public void getHotKey() {
        mModel.getHotKey(new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.getHotKeySuccess((List<HotKeyBean>) baseData.getData());
            }

            @Override
            public void fail(Throwable throwable) {
                mView.getHistoryFail(throwable.getMessage());
            }
        });
    }

    @Override
    public void getHotKeycache() {
        mModel.hotKeyFromCache(new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.getHotKeySuccess((List<HotKeyBean>) baseData.getData());
            }

            @Override
            public void fail(Throwable throwable) {
                mView.getHistoryFail(throwable.getMessage());
            }
        });
    }

    @Override
    public void getHistory() {
        mModel.getHistory(new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.getHistorySuccess((List<String>) baseData.getData());
            }

            @Override
            public void fail(Throwable throwable) {
                mView.getHistoryFail(throwable.getMessage());
            }
        });
    }

    @Override
    public void searchArticle(int page, String key) {
        mModel.searchArticle(page, key, new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.searchSuccess((ArticleBean) baseData.getData());
            }

            @Override
            public void fail(Throwable throwable) {
                mView.searchFail(throwable.getMessage());
            }
        });
    }

    @Override
    protected SearchModel createModel() {
        return new SearchModel();
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
    public void collectOutside(int position, View view, String author, String title, String link) {

    }

    @Override
    public void unCollect(int position, View view, int articleId) {
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
    public void unCollectFromCollect(int position, View view, int articleId, int originId) {

    }
}
