package com.example.wanandroid.mvp.me.presenter;

import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.HeaderBean;
import com.example.wanandroid.mvp.me.contract.NavigationContract;
import com.example.wanandroid.mvp.me.model.NavigationModel;
import com.example.wanandroid.mvp.me.view.NavigationActivity;
import com.example.wanandroid.network.ModelCallback;
import com.pgaofeng.common.base.BasePresenter;

import java.util.List;

/**
 * @author gaofengpeng
 * @date 2019/8/12
 * @description :
 */
public class NavigationPresenter extends BasePresenter<NavigationActivity, NavigationModel> implements NavigationContract.Presenter {
    public NavigationPresenter(NavigationActivity view) {
        super(view);
    }

    @Override
    public void gatNavigation() {
        mModel.getNavigation(new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.getNavigationSuccess((List<HeaderBean>) baseData.getData());
            }

            @Override
            public void fail(Throwable throwable) {
                mView.getNavigationFail(throwable.getMessage());
            }
        });
    }

    @Override
    public void getNaviCache() {
        mModel.getNaviCache(new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.getNavigationSuccess((List<HeaderBean>) baseData.getData());
            }

            @Override
            public void fail(Throwable throwable) {
                mView.getNavigationFail(throwable.getMessage());
            }
        });
    }

    @Override
    protected NavigationModel createModel() {
        return new NavigationModel();
    }
}
