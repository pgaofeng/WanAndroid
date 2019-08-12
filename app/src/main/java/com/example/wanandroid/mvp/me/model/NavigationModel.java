package com.example.wanandroid.mvp.me.model;

import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.NavigationBean;
import com.example.wanandroid.mvp.me.contract.NavigationContract;
import com.example.wanandroid.network.ModelCallback;
import com.example.wanandroid.network.RetrofitClient;
import com.example.wanandroid.service.MeService;
import com.pgaofeng.common.base.BaseModel;
import com.pgaofeng.common.network.BaseObserver;

import java.util.List;

/**
 * @author gaofengpeng
 * @date 2019/8/12
 * @description :
 */
public class NavigationModel extends BaseModel implements NavigationContract.Model {
    @Override
    public void getNavigation(ModelCallback callback) {
        RetrofitClient.getInstance()
                .createService(MeService.class)
                .getNavigation()
                .compose(switchThread())
                .subscribe(new BaseObserver<BaseResponse<List<NavigationBean>>>(mDisposableManager) {
                    @Override
                    public void onSuccess(BaseResponse<List<NavigationBean>> listBaseResponse) {
                        callback.success(listBaseResponse);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callback.fail(throwable);
                    }
                });
    }
}
