package com.example.wanandroid.mvp.me.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.CollectWebsiteBean;
import com.example.wanandroid.mvp.me.adapter.CollectWebsiteAdapter;
import com.example.wanandroid.mvp.me.contract.CommonlyContract;
import com.example.wanandroid.mvp.me.presenter.CommonlyPresenter;
import com.pgaofeng.common.base.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author gaofengpeng
 * @date 2019/8/9
 * @description :常用网站界面
 */
public class CommonlyWebsiteActivity extends BaseActivity<CommonlyPresenter> implements CommonlyContract.View {


    @BindView(R.id.me_collect_website_back)
    FrameLayout mMeCollectWebsiteBack;
    @BindView(R.id.me_collect_website_recycler)
    RecyclerView mMeCollectWebsiteRecycler;
    @BindView(R.id.me_collect_website_refresh)
    SmartRefreshLayout mMeCollectWebsiteRefresh;

    private CollectWebsiteAdapter mAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_commonly_website;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected CommonlyPresenter createPresenter() {
        return new CommonlyPresenter(this);
    }

    @Override
    public void getCommonlyWebsiteSuccess(List<CollectWebsiteBean> data) {
        mMeCollectWebsiteRefresh.finishRefresh();
        mAdapter.setData(data);
    }

    @Override
    public void getCommonlyWebsiteFail(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mMeCollectWebsiteBack.setOnClickListener(v -> finish());
        mMeCollectWebsiteRefresh.setEnableLoadMore(false);
        mMeCollectWebsiteRefresh.setOnRefreshListener(refreshLayout -> mPresenter.getCommonlyWebsite());
        mMeCollectWebsiteRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new CollectWebsiteAdapter(mContext);
        mMeCollectWebsiteRecycler.setAdapter(mAdapter);
        mPresenter.getCommonlyWebsite();
    }
}
