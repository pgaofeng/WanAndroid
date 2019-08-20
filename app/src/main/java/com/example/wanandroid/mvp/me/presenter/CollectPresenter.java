package com.example.wanandroid.mvp.me.presenter;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.CollectWebsiteBean;
import com.example.wanandroid.bean.DatasBean;
import com.example.wanandroid.mvp.login.LoginException;
import com.example.wanandroid.mvp.login.view.LoginActivity;
import com.example.wanandroid.mvp.me.contract.CollectContract;
import com.example.wanandroid.mvp.me.model.CollectModel;
import com.example.wanandroid.network.ModelCallback;
import com.pgaofeng.common.base.BasePresenter;

import java.util.List;

/**
 * @author gaofengpeng
 * @date 2019/8/6
 * @description :
 */
public class CollectPresenter extends BasePresenter<CollectContract.View, CollectModel> implements CollectContract.Presenter {
    public CollectPresenter(CollectContract.View view) {
        super(view);
    }

    @Override
    public void getCollectList(int page) {
        mModel.getCollectList(page, new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.getCollectListSuccess((ArticleBean) baseData.getData());
            }

            @Override
            public void fail(Throwable throwable) {
                if (throwable instanceof LoginException) {
                    mView.toLogin(LoginActivity.class);
                }
                mView.getCollectFail(throwable.getMessage());
            }
        });
    }

    @Override
    public void getCollectWebsite() {
        mModel.getCollectWebsite(new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.getCollectWebsiteSuccess((List<CollectWebsiteBean>) baseData.getData());
            }

            @Override
            public void fail(Throwable throwable) {
                if (throwable instanceof LoginException) {
                    mView.toLogin(LoginActivity.class);
                }
                mView.getCollectWebsiteFail(throwable.getMessage());
            }
        });
    }

    @Override
    public void getCollectListCache() {
        mModel.getCollectListCache(new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.getCollectListCacheSuccess((List<DatasBean>) baseData.getData());
            }

            @Override
            public void fail(Throwable throwable) {
                mView.getCollectFail(throwable.getMessage());
            }
        });
    }

    @Override
    public void getCollectWebCache() {
        mModel.getCollectWebCache(new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.getCollectWebCacheSuccess((List<CollectWebsiteBean>) baseData.getData());
            }

            @Override
            public void fail(Throwable throwable) {
                mView.getCollectWebsiteFail(throwable.getMessage());
            }
        });
    }

    @Override
    protected CollectModel createModel() {
        return new CollectModel();
    }
}
