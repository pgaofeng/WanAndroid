package com.example.wanandroid.mvp.type.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.TypeBean;
import com.example.wanandroid.mvp.home.adapter.ArticleAdapter;
import com.example.wanandroid.mvp.type.contract.TypeContract;
import com.example.wanandroid.mvp.type.presenter.TypePresenter;
import com.example.wanandroid.mvp.web.WebActivity;
import com.example.wanandroid.util.EventBusUtils;
import com.pgaofeng.common.base.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author gaofengpeng
 * @date 2019/8/9
 * @description :分类下的文章列表
 */
public class TypeArticleActivity extends BaseActivity<TypePresenter> implements TypeContract.View {


    @BindView(R.id.home_title)
    TextView mHomeTitle;
    @BindView(R.id.me_collect_website_back)
    FrameLayout mMeCollectWebsiteBack;
    @BindView(R.id.home_recycler)
    RecyclerView mHomeRecycler;
    @BindView(R.id.home_refresh)
    SmartRefreshLayout mHomeRefresh;


    private ArticleAdapter mAdapter;
    private int page = 0;
    private int cid;
    private boolean isLoadMore = false;
    private String title = "";


    @Override
    protected int getContentView() {
        return R.layout.activity_type_article;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected TypePresenter createPresenter() {
        return new TypePresenter(this);
    }

    @Override
    public void getTypeListSuccess(List<TypeBean> datas) {

    }

    @Override
    public void getTypeListFail(String message) {

    }

    @Override
    public void getTypeArticleSuccess(ArticleBean bean) {
        if (bean != null && bean.getDatas() != null) {
            if (bean.isOver()) {
                mHomeRefresh.finishLoadMoreWithNoMoreData().finishRefresh();
            } else {
                mHomeRefresh.finishLoadMore().finishRefresh();
            }
            if (isLoadMore) {
                this.isLoadMore = false;
                mAdapter.addDatas(bean.getDatas());
            } else {
                mAdapter.setDatas(bean.getDatas(), null);
            }
        }
    }

    @Override
    public void getTypeArticleFail(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        mHomeRefresh.finishRefresh(false).finishLoadMore(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        EventBusUtils.register(this);
        init();
    }

    @Subscribe
    public void onEvent(String message) {
        if (EventBusUtils.LOGIN_SUCCESS.equals(message)) {
            mHomeRefresh.autoRefresh();
        }
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

    private void init() {

        this.cid = getIntent().getIntExtra("cid", -1);
        this.title = getIntent().getStringExtra("title");
        if (cid == -1) {
            Toast.makeText(mContext, "cid异常", Toast.LENGTH_SHORT).show();
        }
        mHomeTitle.setText(title);

        mMeCollectWebsiteBack.setOnClickListener(v -> finish());
        mAdapter = new ArticleAdapter(mContext);
        mHomeRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mHomeRecycler.setAdapter(mAdapter);
        mHomeRefresh.setOnRefreshListener(refreshLayout -> {
            this.page = 0;
            mPresenter.getTypeArticle(page, cid);
        });
        mHomeRefresh.setOnLoadMoreListener(refreshLayout -> {
            this.isLoadMore = true;
            this.page++;
            mPresenter.getTypeArticle(page, cid);
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
        mPresenter.getTypeArticle(page, cid);
    }
}
