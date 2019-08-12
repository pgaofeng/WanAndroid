package com.example.wanandroid.mvp.me.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.mvp.login.view.LoginActivity;
import com.example.wanandroid.util.EventBusUtils;
import com.pgaofeng.common.base.BaseFragment;
import com.pgaofeng.common.mvp.Presenter;

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
public class MeFragment extends BaseFragment {


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


    @Override
    protected int getContentView() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView(View view) {
    }

    @Override
    protected Presenter createPresenter() {
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        EventBusUtils.register(this);
        mPreferences = mContext.getSharedPreferences(LoginActivity.LOGIN_STORE, Context.MODE_PRIVATE);
        init();
        return rootView;
    }

    @SuppressLint("SetTextI18n")
    private void init() {
        if (mPreferences.getBoolean("new", false)) {
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
                startActivity(new Intent(mContext, CollectActivity.class));
                break;
            // 收集网址
            case R.id.me_collect_website:
                startActivity(new Intent(mContext, CollectWebsiteActivity.class));
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
                startActivity(new Intent(mContext, TodoActivity.class));
                break;
            //　我的
            case R.id.me_about:
                // TODO 跳转关于我界面
                break;
            default:
                break;
        }
    }

}
