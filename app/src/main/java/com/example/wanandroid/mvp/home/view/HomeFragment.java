package com.example.wanandroid.mvp.home.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.mvp.home.adapter.ArticleAdapter;
import com.example.wanandroid.mvp.home.contract.HomeContract;
import com.example.wanandroid.mvp.home.presenter.HomePresenter;
import com.pgaofeng.common.base.BaseFragment;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

/**
 * @author HomeFragment
 * @date 2019/7/28
 * 首页
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    RecyclerView mHomeRecycler;
    RefreshLayout mRefreshLayout;
    /**
     * 搜索图片，点击进入搜索界面
     */
    FrameLayout mSearch;

    private ArticleAdapter mAdapter;
    /**
     * 分页页数
     */
    private int page = 0;
    private boolean isLoadMore = false;

    @Override
    protected int getContentView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        mHomeRecycler = view.findViewById(R.id.home_recycler);
        mSearch = view.findViewById(R.id.home_search);
        mRefreshLayout = view.findViewById(R.id.home_refresh);
        mAdapter = new ArticleAdapter(mContext);
        mHomeRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mHomeRecycler.setAdapter(mAdapter);

        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            this.page = 0;
            mPresenter.getTopArticleList();
            mPresenter.getArticleList(page);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            this.page++;
            this.isLoadMore = true;
            mPresenter.getArticleList(page);
        });

        mSearch.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, SearchActivity.class);
            startActivity(intent);
        });

        mPresenter.getTopArticleList();
        mPresenter.getArticleList(page);
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void getArticleSuccess(ArticleBean bean) {
        if (bean.isOver()) {
            mRefreshLayout.finishLoadMoreWithNoMoreData();
        } else {
            mRefreshLayout.finishLoadMore();
        }
        mRefreshLayout.finishRefresh();
        if (isLoadMore) {
            mAdapter.addDatas(bean.getDatas());
            isLoadMore = false;
        } else {
            mAdapter.setDatas(bean.getDatas(), null);
        }
    }

    @Override
    public void getArticleFail(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getTopArticleListSuccess(List<ArticleBean.DatasBean> bean) {
        mAdapter.setDatas(null, bean);
    }

    @Override
    public void getTopArticleListFail(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
}
