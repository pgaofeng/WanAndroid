package com.example.wanandroid.mvp.login.model;

import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.LoginBean;
import com.example.wanandroid.mvp.login.contract.LoginContract;
import com.example.wanandroid.network.BaseObserver;
import com.example.wanandroid.network.ModelCallback;
import com.example.wanandroid.network.RetrofitClient;
import com.example.wanandroid.service.LoginService;
import com.pgaofeng.common.base.BaseModel;

/**
 * @author LoginModel
 * @date 2019/8/2
 * ${DESCRIPTION}
 */
public class LoginModel extends BaseModel implements LoginContract.Model {

    @Override
    public void login(String username, String password, ModelCallback callback) {
        RetrofitClient.getInstance()
                .createService(LoginService.class)
                .login(username, password)
                .compose(switchThread())
                .subscribe(new BaseObserver<BaseResponse<LoginBean>>(mDisposableManager) {
                    @Override
                    public void onSuccess(BaseResponse<LoginBean> loginBeanBaseResponse) {
                        callback.success(loginBeanBaseResponse);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callback.fail(throwable);
                    }
                });
    }

    @Override
    public void logout(ModelCallback callback) {
        RetrofitClient.getInstance()
                .createService(LoginService.class)
                .logout()
                .compose(switchThread())
                .subscribe(new BaseObserver<BaseResponse>(mDisposableManager) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        callback.success(baseResponse);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callback.fail(throwable);
                    }
                });
    }
}
