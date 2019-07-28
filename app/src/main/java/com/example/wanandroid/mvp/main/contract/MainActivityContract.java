package com.example.wanandroid.mvp.main.contract;

/**
 * @author MainActivityContract
 * @date 2019/7/28
 * 主页面对应的Contract
 */
public interface MainActivityContract {
    interface View {
        /**
         * 切换对应的页面
         *
         * @param index 切换的下标
         */
        void switchTab(int index);
    }
}
