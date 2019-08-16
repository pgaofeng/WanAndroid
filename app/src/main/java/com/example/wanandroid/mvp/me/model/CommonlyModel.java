package com.example.wanandroid.mvp.me.model;

import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.CollectWebsiteBean;
import com.example.wanandroid.mvp.me.contract.CommonlyContract;
import com.example.wanandroid.network.BaseObserver;
import com.example.wanandroid.network.ModelCallback;
import com.example.wanandroid.network.RetrofitClient;
import com.example.wanandroid.service.MeService;
import com.pgaofeng.common.base.BaseModel;

import java.util.List;

/**
 * @author gaofengpeng
 * @date 2019/8/9
 * @description :
 */
public class CommonlyModel extends BaseModel implements CommonlyContract.Model {
    @Override
    public void getCommonlyWebsite(ModelCallback callback) {
        RetrofitClient.getInstance()
                .createService(MeService.class)
                .getCommonlyWebsite()
                .compose(switchThread())
                .subscribe(new BaseObserver<BaseResponse<List<CollectWebsiteBean>>>(mDisposableManager) {
                    @Override
                    public void onSuccess(BaseResponse<List<CollectWebsiteBean>> listBaseResponse) {
                        callback.success(listBaseResponse);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callback.fail(throwable);
                    }
                });
    }
}
