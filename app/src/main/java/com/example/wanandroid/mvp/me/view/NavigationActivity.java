package com.example.wanandroid.mvp.me.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.HeaderBean;
import com.example.wanandroid.mvp.me.adapter.NavigationAdapter;
import com.example.wanandroid.mvp.me.adapter.NavigationDecoration;
import com.example.wanandroid.mvp.me.contract.NavigationContract;
import com.example.wanandroid.mvp.me.presenter.NavigationPresenter;
import com.example.wanandroid.mvp.web.WebActivity;
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

    private NavigationAdapter adapter;

    @Override
    public void getNavigationSuccess(List<HeaderBean> data) {
        mItemNavigationRefresh.finishRefresh();
        adapter.setData(data);
    }

    @Override
    public void getNavigationFail(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        mItemNavigationRefresh.finishRefresh(false);
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
        mItemNavigationRefresh.setOnRefreshListener(refreshLayout -> mPresenter.gatNavigation());
        mItemNavigationRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new NavigationAdapter(mContext);
        mItemNavigationRecycler.addItemDecoration(new NavigationDecoration(mContext));
        mItemNavigationRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener((position, view) -> {
            Intent intent = new Intent(mContext, WebActivity.class);
            intent.putExtra("link", adapter.getBean(position).getLink());
            mContext.startActivity(intent);
        });

        mPresenter.gatNavigation();

    }
}
