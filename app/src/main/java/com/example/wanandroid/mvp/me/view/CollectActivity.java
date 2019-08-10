package com.example.wanandroid.mvp.me.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.CollectWebsiteBean;
import com.example.wanandroid.mvp.home.adapter.ArticleAdapter;
import com.example.wanandroid.mvp.me.adapter.CollectAdapter;
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
 * @description :收藏文章界面
 */
public class CollectActivity extends BaseActivity<CollectPresenter> implements CollectContract.View {

    @BindView(R.id.me_collect_recycler)
    RecyclerView mMeCollectRecycler;
    @BindView(R.id.me_collect_refresh)
    SmartRefreshLayout mMeCollectRefresh;

    private ArticleAdapter mAdapter;
    private int page = 0;
    private boolean isLoadMore = false;

    @Override
    public void getCollectListSuccess(ArticleBean bean) {
        if (bean.isOver()) {
            mMeCollectRefresh.finishLoadMoreWithNoMoreData();
        } else {
            mMeCollectRefresh.finishLoadMore();
        }
        mMeCollectRefresh.finishRefresh();
        if (isLoadMore) {
            this.isLoadMore = false;
            mAdapter.addDatas(bean.getDatas());
        } else {
            mAdapter.setDatas(bean.getDatas(), null);
        }
    }

    @Override
    public void getCollectFail(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCollectWebsiteSuccess(List<CollectWebsiteBean> list) {

    }

    @Override
    public void getCollectWebsiteFail(String message) {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_collect;
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
        mAdapter = new CollectAdapter(mContext);
        mMeCollectRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mMeCollectRecycler.setAdapter(mAdapter);
        mMeCollectRefresh.setOnRefreshListener(refreshLayout -> {
            this.page = 0;
            mPresenter.getCollectList(page);
        });
        mMeCollectRefresh.setOnLoadMoreListener(refreshLayout -> {
            this.page++;
            this.isLoadMore = true;
            mPresenter.getCollectList(page);
        });
        mPresenter.getCollectList(page);
    }

    @OnClick(R.id.me_collect_back)
    public void onViewClicked() {
        finish();
    }
}
