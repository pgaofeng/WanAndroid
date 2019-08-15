package com.example.wanandroid.mvp.wechat.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.mvp.home.adapter.ArticleAdapter;
import com.example.wanandroid.mvp.web.WebActivity;
import com.example.wanandroid.mvp.wechat.contract.WeChatArticleContract;
import com.example.wanandroid.mvp.wechat.presenter.WeChatArticlePresenter;
import com.example.wanandroid.util.EventBusUtils;
import com.pgaofeng.common.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.Subscribe;

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
        mAdapter.setOnCollectClickListener((position, v, articleId, isCollect) -> {
            if (isCollect) {
                mPresenter.collectInside(position, v, articleId);
            } else {
                mPresenter.unCollect(position, v, articleId);
            }
        });
        mAdapter.setOnItemClickListener(link -> {
            Intent intent = new Intent(mContext, WebActivity.class);
            intent.putExtra("link", link);
            mContext.startActivity(intent);
        });
        EventBusUtils.register(this);

        mPresenter.getArticleList(id, page);
    }

    @Override
    public void collectSuccess(int position, View view) {

    }

    @Override
    public void collectFail(int position, View view) {
        Toast.makeText(mContext, "收藏失败！", Toast.LENGTH_SHORT).show();
        mAdapter.setCollect(false, position, view);
    }

    @Override
    public void unCollectSuccess(int position, View view) {

    }

    @Override
    public void unCollectFail(int position, View view) {
        Toast.makeText(mContext, "取消收藏失败！", Toast.LENGTH_SHORT).show();
        mAdapter.setCollect(true, position, view);
    }

    @Override
    protected WeChatArticlePresenter createPresenter() {
        return new WeChatArticlePresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBusUtils.unRegister(this);
    }

    @Subscribe
    public void onEvent(String message) {
        if (EventBusUtils.LOGIN_SUCCESS.equals(message)) {
            mRefreshLayout.autoRefresh();
        }
    }
}
