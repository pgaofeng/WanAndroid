package com.example.wanandroid.mvp.me.presenter;

import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.CollectWebsiteBean;
import com.example.wanandroid.mvp.me.contract.CommonlyContract;
import com.example.wanandroid.mvp.me.model.CommonlyModel;
import com.example.wanandroid.mvp.me.view.CommonlyWebsiteActivity;
import com.example.wanandroid.network.ModelCallback;
import com.pgaofeng.common.base.BasePresenter;

import java.util.List;

/**
 * @author gaofengpeng
 * @date 2019/8/9
 * @description :
 */
public class CommonlyPresenter extends BasePresenter<CommonlyWebsiteActivity, CommonlyModel> implements CommonlyContract.Presenter {
    public CommonlyPresenter(CommonlyWebsiteActivity view) {
        super(view);
    }

    @Override
    public void getCommonlyWebsite() {
        mModel.getCommonlyWebsite(new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.getCommonlyWebsiteSuccess((List<CollectWebsiteBean>) baseData.getData());
            }

            @Override
            public void fail(Throwable throwable) {
                mView.getCommonlyWebsiteFail(throwable.getMessage());
            }
        });
    }

    @Override
    protected CommonlyModel createModel() {
        return new CommonlyModel();
    }
}
