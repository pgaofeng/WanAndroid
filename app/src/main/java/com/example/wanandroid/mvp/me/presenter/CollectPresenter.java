package com.example.wanandroid.mvp.me.presenter;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.mvp.me.contract.CollectContract;
import com.example.wanandroid.mvp.me.model.CollectModel;
import com.example.wanandroid.mvp.me.view.CollectActivity;
import com.example.wanandroid.network.ModelCallback;
import com.pgaofeng.common.base.BasePresenter;

/**
 * @author gaofengpeng
 * @date 2019/8/6
 * @description :
 */
public class CollectPresenter extends BasePresenter<CollectActivity, CollectModel> implements CollectContract.Presenter {
    public CollectPresenter(CollectActivity view) {
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
                mView.getCollectFail(throwable.getMessage());
            }
        });
    }

    @Override
    protected CollectModel createModel() {
        return new CollectModel();
    }
}
