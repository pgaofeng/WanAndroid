package com.example.wanandroid.mvp.me.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.LoginBean;
import com.example.wanandroid.mvp.login.contract.LoginContract;
import com.example.wanandroid.mvp.login.presenter.LoginPresenter;
import com.example.wanandroid.mvp.login.view.LoginActivity;
import com.example.wanandroid.network.cookie.MyCookieStore;
import com.example.wanandroid.util.EventBusUtils;
import com.pgaofeng.common.base.BaseFragment;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author MeFragment
 * @date 2019/7/28
 * 我的
 */
public class MeFragment extends BaseFragment<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.me_collect)
    RelativeLayout mMeCollect;
    Unbinder unbinder;
    @BindView(R.id.me_nickName)
    TextView mMeNickName;
    @BindView(R.id.me_id)
    TextView mMeId;
    @BindView(R.id.me_loginOrRegister)
    TextView mMeLoginOrRegister;
    @BindView(R.id.me_info)
    LinearLayout mMeInfo;

    private SharedPreferences mPreferences;

    private boolean isLogin;


    @Override
    protected int getContentView() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView(View view) {
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        EventBusUtils.register(this);
        mPreferences = mContext.getSharedPreferences(LoginActivity.LOGIN_STORE, Context.MODE_PRIVATE);
        init();
        return rootView;
    }

    @SuppressLint("SetTextI18n")
    private void init() {
        isLogin = mPreferences.getBoolean("new", false);
        if (isLogin) {
            mMeLoginOrRegister.setVisibility(View.GONE);
            mMeInfo.setVisibility(View.VISIBLE);
            mMeNickName.setText(mPreferences.getString("nickname", ""));
            mMeId.setText("id:" + mPreferences.getInt("id", -1));
        } else {
            mMeLoginOrRegister.setVisibility(View.VISIBLE);
            mMeInfo.setVisibility(View.GONE);
            mMeLoginOrRegister.setOnClickListener(v -> {
                toLogin(LoginActivity.class);
            });
        }
    }

    @Subscribe
    public void onEvent(String message) {
        if (EventBusUtils.LOGIN_SUCCESS.equals(message)) {
            init();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBusUtils.unRegister(this);
        unbinder.unbind();
    }

    @OnClick({R.id.me_collect, R.id.me_navigation, R.id.me_todo, R.id.me_about, R.id.me_collect_website, R.id.me_commonly_website})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 收藏文章
            case R.id.me_collect:
                if (isLogin)
                    startActivity(new Intent(mContext, CollectActivity.class));
                else
                    toLogin(LoginActivity.class);
                break;
            // 收集网址
            case R.id.me_collect_website:
                if (isLogin)
                    startActivity(new Intent(mContext, CollectWebsiteActivity.class));
                else
                    toLogin(LoginActivity.class);
                break;
            // 常用网站
            case R.id.me_commonly_website:
                startActivity(new Intent(mContext, CommonlyWebsiteActivity.class));
                break;
            // 导航
            case R.id.me_navigation:
                startActivity(new Intent(mContext, NavigationActivity.class));
                break;
            // TODO界面
            case R.id.me_todo:
                if (isLogin)
                    startActivity(new Intent(mContext, TodoActivity.class));
                else
                    toLogin(LoginActivity.class);
                break;
            //　我的（已修改为退出登录）
            case R.id.me_about:
                new AlertDialog.Builder(mContext)
                        .setTitle("提示")
                        .setMessage("退出登录后部分功能将不能使用，是否确认退出登录？")
                        .setPositiveButton("确定", (dialog, which) -> logout())
                        .setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
                        .create()
                        .show();
                break;
            default:
                break;
        }
    }

    /**
     * 退出登录
     */
    private void logout() {
        mPresenter.logout();
    }

    @Override
    public void loginSuccess(LoginBean bean) {

    }

    @Override
    public void loginFail(String message) {

    }

    @Override
    public void logoutSuccess() {
        mPreferences.edit().clear().apply();
        mContext.getSharedPreferences(MyCookieStore.COOKIE_STORE_NAME, Context.MODE_PRIVATE).edit().clear().apply();
        init();
    }
}
