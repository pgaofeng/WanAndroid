package com.example.wanandroid.mvp.wechat.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.mvp.home.adapter.ArticleAdapter;
import com.example.wanandroid.mvp.wechat.contract.WeChatArticleContract;
import com.example.wanandroid.mvp.wechat.presenter.WeChatArticlePresenter;
import com.pgaofeng.common.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * @author gaofengpeng
 * @date 2019/8/2
 * @description :
 */
public class WeCharArticleFragment extends BaseFragment<WeChatArticlePresenter> implements WeChatArticleContract.View {

    private int id;
    private int page = 0;

    private RecyclerView mRecyclerView;
    private ArticleAdapter mAdapter;
    private SmartRefreshLayout mRefreshLayout;
    private boolean isLoadMore = false;

    @Override
    public void getArticleListSuccess(ArticleBean bean) {
        if (bean.isOver()) {
            mRefreshLayout.finishLoadMoreWithNoMoreData();
        } else {
            mRefreshLayout.finishLoadMore();
        }
        mRefreshLayout.finishRefresh();

        if (isLoadMore) {
            this.isLoadMore = false;
            mAdapter.addDatas(bean.getDatas());
        } else {
            mAdapter.setDatas(bean.getDatas(), null);
        }

    }

    @Override
    public void getArticleListFail(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_wechat_detail;
    }

    @Override
    protected void initView(View view) {
        this.id = getArguments().getInt("id", -1);
        mAdapter = new ArticleAdapter(mContext);
        mRecyclerView = view.findViewById(R.id.weChat_detail_recycler);
        mRefreshLayout = view.findViewById(R.id.weChat_detail_refresh);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);

        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            this.page = 0;
            mPresenter.getArticleList(id, this.page);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            this.page++;
            this.isLoadMore = true;
            mPresenter.getArticleList(id, page);
        });

        mPresenter.getArticleList(id, page);
    }

    @Override
    protected WeChatArticlePresenter createPresenter() {
        return new WeChatArticlePresenter(this);
    }
}
