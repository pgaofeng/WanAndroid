package com.example.wanandroid.mvp.me.presenter;

import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.HeaderBean;
import com.example.wanandroid.bean.NavigationBean;
import com.example.wanandroid.mvp.me.contract.NavigationContract;
import com.example.wanandroid.mvp.me.model.NavigationModel;
import com.example.wanandroid.mvp.me.view.NavigationActivity;
import com.example.wanandroid.network.ModelCallback;
import com.pgaofeng.common.base.BasePresenter;

import java.util.ArrayList;
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
                List<NavigationBean> data = (List<NavigationBean>) baseData.getData();
                List<HeaderBean> beans = new ArrayList<>();
                for (NavigationBean bean : data) {
                    for (int i = 0; i < bean.getArticles().size(); i++) {
                        HeaderBean headerBean = new HeaderBean();
                        headerBean.setName(bean.getArticles().get(i).getTitle());
                        headerBean.setLink(bean.getArticles().get(i).getLink());
                        headerBean.setHeaderName(bean.getName());
                        headerBean.setHeader(i == 0);
                        beans.add(headerBean);
                    }
                }
                mView.getNavigationSuccess(beans);
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
