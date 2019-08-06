package com.example.wanandroid.mvp.me.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.mvp.home.adapter.ArticleAdapter;
import com.example.wanandroid.mvp.me.contract.CollectContract;
import com.example.wanandroid.mvp.me.presenter.CollectPresenter;
import com.pgaofeng.common.base.BaseActivity;

public class CollectActivity extends BaseActivity<CollectPresenter> implements CollectContract.View {

    private ArticleAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    public void getCollectListSuccess(ArticleBean bean) {
        mAdapter.setDatas(bean.getDatas(),null);
    }

    @Override
    public void getCollectFail(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_collect;
    }

    @Override
    protected void initView() {
        mRecyclerView = findViewById(R.id.me_collect_recycler);
        mAdapter = new ArticleAdapter(mContext);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.getCollectList(0);
    }

    @Override
    protected CollectPresenter createPresenter() {
        return new CollectPresenter(this);
    }
}
