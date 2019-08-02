package com.example.wanandroid.mvp.login.presenter;

import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.LoginBean;
import com.example.wanandroid.mvp.login.contract.LoginContract;
import com.example.wanandroid.mvp.login.model.LoginModel;
import com.example.wanandroid.mvp.login.view.LoginActivity;
import com.example.wanandroid.network.ModelCallback;
import com.pgaofeng.common.base.BasePresenter;

/**
 * @author LoginPresenter
 * @date 2019/8/2
 * ${DESCRIPTION}
 */
public class LoginPresenter extends BasePresenter<LoginActivity, LoginModel> implements LoginContract.Presenter {


    public LoginPresenter(LoginActivity view) {
        super(view);
    }

    @Override
    public void login(String username, String password) {
        mModel.login(username, password, new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.loginSuccess((LoginBean) baseData.getData());
            }

            @Override
            public void fail(Throwable throwable) {
                mView.loginFail(throwable.getMessage());
            }
        });
    }

    @Override
    protected LoginModel createModel() {
        return new LoginModel();
    }
}
