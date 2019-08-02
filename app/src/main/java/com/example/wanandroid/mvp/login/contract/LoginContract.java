package com.example.wanandroid.mvp.login.contract;

import com.example.wanandroid.bean.LoginBean;
import com.example.wanandroid.network.ModelCallback;

/**
 * @author LoginContract
 * @date 2019/8/2
 * ${DESCRIPTION}
 */
public interface LoginContract {
    public interface View {
        /**
         * 登录成功
         *
         * @param bean 登录成功数据
         */
        void loginSuccess(LoginBean bean);

        /**
         * 登录失败
         *
         * @param message 失败信息
         */
        void loginFail(String message);
    }

    public interface Model {
        /**
         * 登录
         *
         * @param username 用户名
         * @param password 密码
         * @param callback 回调接口
         */
        void login(String username, String password, ModelCallback callback);
    }

    public interface Presenter {
        /**
         * 登录
         *
         * @param username 用户名
         * @param password 密码
         */
        void login(String username, String password);
    }
}
