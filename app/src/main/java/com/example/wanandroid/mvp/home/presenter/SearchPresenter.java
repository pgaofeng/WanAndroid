package com.example.wanandroid.mvp.home.presenter;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.HotKeyBean;
import com.example.wanandroid.mvp.home.Model.SearchModel;
import com.example.wanandroid.mvp.home.contract.SearchContract;
import com.example.wanandroid.mvp.home.view.SearchActivity;
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
}
