package com.example.wanandroid.mvp.me;

import android.view.View;

import com.example.wanandroid.R;
import com.pgaofeng.common.base.BaseFragment;
import com.pgaofeng.common.mvp.Presenter;

/**
 * @author MeFragment
 * @date 2019/7/28
 * 我的
 */
public class MeFragment extends BaseFragment {

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
}
