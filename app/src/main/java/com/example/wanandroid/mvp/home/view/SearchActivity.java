package com.example.wanandroid.mvp.home.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.HotKeyBean;
import com.example.wanandroid.mvp.home.adapter.HistoryAdapter;
import com.example.wanandroid.mvp.home.contract.SearchContract;
import com.example.wanandroid.mvp.home.presenter.SearchPresenter;
import com.example.wanandroid.util.ScreenUtils;
import com.google.android.flexbox.FlexboxLayout;
import com.pgaofeng.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author gaofengpeng
 * @date 2019/8/6
 * @description :搜索界面
 */
public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View {
    @BindView(R.id.home_search_hotKey_flex)
    FlexboxLayout mHomeSearchHotKeyFlex;
    @BindView(R.id.home_search_hotKey)
    LinearLayout mHomeSearchHotKey;
    @BindView(R.id.home_search_clear)
    TextView mHomeSearchClear;
    @BindView(R.id.home_search_history_recycler)
    RecyclerView mHomeSearchHistoryRecycler;
    @BindView(R.id.home_search_history)
    LinearLayout mHomeSearchHistory;
    @BindView(R.id.home_search_search)
    LinearLayout mHomeSearchSearch;
    @BindView(R.id.home_search_article)
    RecyclerView mHomeSearchArticle;

    private HistoryAdapter mAdapter;

    @Override
    public void getHotKeySuccess(List<HotKeyBean> list) {
        mHomeSearchHotKeyFlex.removeAllViews();
        for (HotKeyBean bean : list) {
            TextView textView = new TextView(mContext);
            ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            int margin = ScreenUtils.dp2px(mContext, 10);
            int padding = ScreenUtils.dp2px(mContext, 6);
            params.setMargins(margin, margin, margin, 0);

            textView.setLayoutParams(params);
            textView.setBackgroundResource(R.drawable.bg_selectable_item);
            textView.setText(bean.getName());
            textView.setClickable(true);
            textView.setPadding(padding, padding, padding, padding);
            mHomeSearchHotKeyFlex.addView(textView);
        }
    }

    @Override
    public void getHotKeyFail(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void searchSuccess(ArticleBean bean) {

    }

    @Override
    public void searchFail(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getHistorySuccess(List<String> list) {
        mAdapter.setData(list);
    }

    @Override
    public void getHistoryFail(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mHomeSearchHistoryRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new HistoryAdapter(mContext);
        mHomeSearchHistoryRecycler.setAdapter(mAdapter);
        mPresenter.getHotKey();
      //  mPresenter.getHistory();
        List<String> stringList = new ArrayList<>();
        for (int i=0;i<10;i++){
            stringList.add("记录"+i);
        }
        mAdapter.setData(stringList);
    }
}
