package com.example.wanandroid.mvp.me.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.wanandroid.R;
import com.pgaofeng.common.base.BaseActivity;
import com.pgaofeng.common.mvp.Presenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author gaofengpeng
 * @date 2019/8/7
 * @description :收藏网址界面
 */
public class CollectWebsiteActivity extends BaseActivity {


    @BindView(R.id.me_collect_website_recycler)
    RecyclerView mMeCollectWebsiteRecycler;
    @BindView(R.id.me_collect_website_refresh)
    SmartRefreshLayout mMeCollectWebsiteRefresh;

    @Override
    protected int getContentView() {
        return R.layout.activity_collect_website;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected Presenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mMeCollectWebsiteRecycler.setLayoutManager(new LinearLayoutManager(mContext));

    }

    @OnClick(R.id.me_collect_website_back)
    public void onViewClicked() {
        finish();
    }
}
