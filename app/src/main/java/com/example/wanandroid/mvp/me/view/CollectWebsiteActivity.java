package com.example.wanandroid.mvp.me.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.CollectWebsiteBean;
import com.example.wanandroid.mvp.me.adapter.CollectWebsiteAdapter;
import com.example.wanandroid.mvp.me.contract.CollectContract;
import com.example.wanandroid.mvp.me.presenter.CollectPresenter;
import com.pgaofeng.common.base.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author gaofengpeng
 * @date 2019/8/7
 * @description :收藏网址界面
 */
public class CollectWebsiteActivity extends BaseActivity<CollectPresenter> implements CollectContract.View {


    @BindView(R.id.me_collect_website_recycler)
    RecyclerView mMeCollectWebsiteRecycler;
    @BindView(R.id.me_collect_website_refresh)
    SmartRefreshLayout mMeCollectWebsiteRefresh;


    private CollectWebsiteAdapter mAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_collect_website;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected CollectPresenter createPresenter() {
        return new CollectPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mAdapter = new CollectWebsiteAdapter(mContext);
        mAdapter.setOnItemClickListener((position, view) -> {
            Toast.makeText(mContext, "点击了" + position, Toast.LENGTH_SHORT).show();
        });
        mMeCollectWebsiteRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mMeCollectWebsiteRecycler.setAdapter(mAdapter);

        mMeCollectWebsiteRefresh.setOnRefreshListener(refreshLayout -> mPresenter.getCollectWebsite());
        mMeCollectWebsiteRefresh.setEnableLoadMore(false);

        mPresenter.getCollectWebsite();


    }

    @OnClick(R.id.me_collect_website_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void getCollectListSuccess(ArticleBean bean) {

    }

    @Override
    public void getCollectFail(String message) {

    }

    @Override
    public void getCollectWebsiteSuccess(List<CollectWebsiteBean> list) {
        mMeCollectWebsiteRefresh.finishRefresh();
        mAdapter.setData(list);
    }

    @Override
    public void getCollectWebsiteFail(String message) {

    }
}
