package com.example.wanandroid.mvp.type.presenter;

import android.view.View;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.TypeBean;
import com.example.wanandroid.mvp.collect_base.CollectModel;
import com.example.wanandroid.mvp.type.contract.TypeContract;
import com.example.wanandroid.mvp.type.model.TypeModel;
import com.example.wanandroid.mvp.type.view.TypeFragment;
import com.example.wanandroid.network.ModelCallback;
import com.pgaofeng.common.base.BasePresenter;

import java.util.List;

/**
 * @author gaofengpeng
 * @date 2019/7/31
 * @description :
 */
public class TypePresenter extends BasePresenter<TypeContract.View, TypeModel> implements TypeContract.Presenter {
    public TypePresenter(TypeContract.View view) {
        super(view);
    }

    @Override
    public void getTypeList() {
        mModel.getTypeList(new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.getTypeListSuccess((List<TypeBean>) baseData.getData());
            }

            @Override
            public void fail(Throwable throwable) {
                mView.getTypeListFail(throwable.getMessage());
            }
        });
    }

    @Override
    public void getTypeArticle(int page, int cid) {
        mModel.getTypeArticle(page, cid, new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.getTypeArticleSuccess((ArticleBean) baseData.getData());
            }

            @Override
            public void fail(Throwable throwable) {
                mView.getTypeArticleFail(throwable.getMessage());
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
                mView.unCollectFail(position, view);
            }
        });
    }

    @Override
    public void unCollectFromCollect(int position, View view,int articleId, int originId) {

    }



    @Override
    protected TypeModel createModel() {
        return new TypeModel();
    }
}
