package com.example.wanandroid.mvp.home.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.mvp.home.adapter.ArticleAdapter;
import com.example.wanandroid.mvp.home.contract.HomeContract;
import com.example.wanandroid.mvp.home.presenter.HomePresenter;
import com.pgaofeng.common.base.BaseFragment;

import butterknife.BindView;

/**
 * @author HomeFragment
 * @date 2019/7/28
 * 首页
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    @BindView(R.id.home_recycler)
    RecyclerView mHomeRecycler;

    private ArticleAdapter mAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        mHomeRecycler = view.findViewById(R.id.home_recycler);
        mAdapter = new ArticleAdapter(mContext);
        mHomeRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mHomeRecycler.setAdapter(mAdapter);
        mPresenter.getArticleList(0);
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void getArticleSuccess(ArticleBean bean) {
        mAdapter.setNewDatas(bean.getDatas());
    }

    @Override
    public void getArticleFail(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
}
