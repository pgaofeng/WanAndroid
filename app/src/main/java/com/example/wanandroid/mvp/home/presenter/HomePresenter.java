package com.example.wanandroid.mvp.home.presenter;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.mvp.collect_base.CollectModel;
import com.example.wanandroid.mvp.home.Model.HomeModel;
import com.example.wanandroid.mvp.home.contract.HomeContract;
import com.example.wanandroid.mvp.home.view.HomeFragment;
import com.example.wanandroid.network.ModelCallback;
import com.pgaofeng.common.base.BasePresenter;

import java.util.List;

/**
 * @author gaofengpeng
 * @date 2019/7/30
 * @description :
 */
public class HomePresenter extends BasePresenter<HomeFragment, HomeModel> implements HomeContract.Presenter {
    public HomePresenter(HomeFragment view) {
        super(view);
    }

    @Override
    public void getArticleList(int page) {

        mModel.getArticleList(page, new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.getArticleSuccess((ArticleBean) baseData.getData());
            }

            @Override
            public void fail(Throwable throwable) {
                mView.getArticleFail(throwable.getMessage());
            }
        });
    }

    @Override
    public void getTopArticleList() {
        mModel.getTopArticleList(new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.getTopArticleListSuccess((List<ArticleBean.DatasBean>) baseData.getData());
            }

            @Override
            public void fail(Throwable throwable) {
                mView.getTopArticleListFail(throwable.getMessage());
            }
        });
    }

    @Override
    public void update() {
        mView.startUpdate();
        Handler handler = new Handler(Looper.getMainLooper());
        mModel.update(new HomeContract.DownLoadListener() {
            @Override
            public void startDownload() {
                handler.post(() -> mView.startUpdate());
            }

            @Override
            public void downLoadProgress(long cur, long total) {
                handler.post(() -> mView.updateProgress(cur, total));
            }

            @Override
            public void downLoadSuccess() {
                handler.post(() -> mView.updateSuccess());
            }

            @Override
            public void downLoadFail(String message) {
                handler.post(() -> mView.updateFail(message));
            }
        });
    }

    @Override
    protected HomeModel createModel() {
        return new HomeModel();
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
                mView.unCollectFail(position, view);
            }
        });
    }

    @Override
    public void unCollectFromCollect(int position, View view, int articleId, int originId) {

    }
}
