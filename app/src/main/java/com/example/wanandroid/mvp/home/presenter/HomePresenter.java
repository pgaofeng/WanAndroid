package com.example.wanandroid.mvp.home.presenter;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BaseResponse;
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
    protected HomeModel createModel() {
        return new HomeModel();
    }
}
