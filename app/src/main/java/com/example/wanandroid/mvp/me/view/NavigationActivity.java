package com.example.wanandroid.mvp.me.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.HeaderBean;
import com.example.wanandroid.mvp.me.contract.NavigationContract;
import com.example.wanandroid.mvp.me.presenter.NavigationPresenter;
import com.pgaofeng.common.base.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author gaofengpeng
 * @date 2019/8/12
 * @description :
 */
public class NavigationActivity extends BaseActivity<NavigationPresenter> implements NavigationContract.View {
    @BindView(R.id.me_navigation_back)
    FrameLayout mMeNavigationBack;
    @BindView(R.id.item_navigation_recycler)
    RecyclerView mItemNavigationRecycler;
    @BindView(R.id.item_navigation_refresh)
    SmartRefreshLayout mItemNavigationRefresh;

    @Override
    public void getNavigationSuccess(List<HeaderBean> data) {

    }

    @Override
    public void getNavigationFail(String message) {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_navigation;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected NavigationPresenter createPresenter() {
        return new NavigationPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mMeNavigationBack.setOnClickListener(v -> finish());
        mItemNavigationRefresh.setEnableLoadMore(false);
        mItemNavigationRecycler.setLayoutManager(new LinearLayoutManager(mContext));

    }
}
