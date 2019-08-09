package com.example.wanandroid.mvp.me.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.wanandroid.R;
import com.pgaofeng.common.base.BaseFragment;
import com.pgaofeng.common.mvp.Presenter;

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
        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
                // TODO 跳转导航界面
                break;
            // TODO界面
            case R.id.me_todo:
                // TODO 跳转TODO界面
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
