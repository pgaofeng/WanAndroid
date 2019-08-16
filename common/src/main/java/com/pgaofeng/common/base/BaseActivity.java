package com.pgaofeng.common.base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.pgaofeng.common.dialog.DialogUtils;
import com.pgaofeng.common.mvp.Presenter;
import com.pgaofeng.common.mvp.View;

/**
 * @author gaofengpeng
 * @date 2019/3/25
 * @description : 基础Activity
 */
public abstract class BaseActivity<P extends Presenter> extends AppCompatActivity implements View {
    protected P mPresenter;
    protected Context mContext;
    private Dialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getContentView());
        mPresenter = createPresenter();
        initView();
        mProgressDialog = DialogUtils.getDefaultDialog(mContext);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detach();
        }
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        if (mProgressDialog != null && !mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog.isShowing() && mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * 返回Content布局id
     *
     * @return 布局文件id
     */
    protected abstract @LayoutRes
    int getContentView();

    /**
     * View的初始化工作
     */
    protected abstract void initView();

    /**
     * 创建Presenter
     *
     * @return 与Activity关联的Presenter
     */
    protected abstract P createPresenter();

    /**
     * 跳转登录界面
     *
     * @param clazz 登录界面
     */
    @Override
    public void toLogin(Class clazz) {
        mContext.startActivity(new Intent(mContext, clazz));
    }
}
