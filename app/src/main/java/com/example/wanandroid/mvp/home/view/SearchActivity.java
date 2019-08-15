package com.example.wanandroid.mvp.home.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.HotKeyBean;
import com.example.wanandroid.mvp.home.adapter.ArticleAdapter;
import com.example.wanandroid.mvp.home.adapter.HistoryAdapter;
import com.example.wanandroid.mvp.home.contract.SearchContract;
import com.example.wanandroid.mvp.home.presenter.SearchPresenter;
import com.example.wanandroid.mvp.web.WebActivity;
import com.example.wanandroid.util.EventBusUtils;
import com.example.wanandroid.util.ScreenUtils;
import com.google.android.flexbox.FlexboxLayout;
import com.pgaofeng.common.base.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.Subscribe;

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
    @BindView(R.id.home_search_search_content)
    LinearLayout mHomeSearchSearchContent;
    @BindView(R.id.home_search_article)
    RecyclerView mHomeSearchArticle;
    @BindView(R.id.home_search_content)
    EditText mHomeSearchContent;
    @BindView(R.id.home_search_back)
    FrameLayout mHomeSearchBack;
    @BindView(R.id.home_search_search)
    TextView mHomeSearchSearch;
    @BindView(R.id.home_search_content_clear)
    ImageView mHomeSearchContentClear;
    @BindView(R.id.home_search_refresh)
    SmartRefreshLayout mHomeSearchRefresh;

    private HistoryAdapter mAdapter;
    private ArticleAdapter mArticleAdapter;
    private int page = 0;
    private boolean isLoadMore = false;
    /**
     * 当前搜索内容
     */
    private String searchContent;

    @Override
    public void getHotKeySuccess(List<HotKeyBean> list) {
        mHomeSearchHotKeyFlex.removeAllViews();
        // 向界面添加搜索热词
        for (HotKeyBean bean : list) {
            TextView textView = new TextView(mContext);
            ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            int margin = ScreenUtils.dp2px(mContext, 5);
            int padding = ScreenUtils.dp2px(mContext, 6);
            params.setMargins(margin, margin, margin, 0);

            textView.setLayoutParams(params);
            textView.setBackgroundResource(R.drawable.bg_selectable_item);
            textView.setText(bean.getName());
            textView.setClickable(true);
            textView.setTextSize(13);
            textView.setPadding(padding, padding, padding, padding);
            textView.setOnClickListener(v -> {
                mHomeSearchContent.setText(bean.getName());
                search(bean.getName());
            });
            mHomeSearchHotKeyFlex.addView(textView);
        }
    }

    @Override
    public void getHotKeyFail(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void searchSuccess(ArticleBean bean) {
        mHomeSearchSearchContent.setVisibility(View.GONE);
        mHomeSearchArticle.setVisibility(View.VISIBLE);
        if (bean.isOver()) {
            mHomeSearchRefresh.finishLoadMoreWithNoMoreData();
        } else {
            mHomeSearchRefresh.finishLoadMore();
        }
        mHomeSearchRefresh.finishRefresh();
        if (isLoadMore) {
            isLoadMore = false;
            mArticleAdapter.addDatas(bean.getDatas());
        } else {
            mArticleAdapter.setDatas(bean.getDatas(), null);
        }
    }

    @Override
    public void searchFail(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        mHomeSearchRefresh.finishRefresh(false).finishLoadMore(false);
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
        EventBusUtils.register(this);
        init();
    }
    // TODO 历史搜索相关，清除所有历史弹窗

    /**
     * 初始化
     */
    private void init() {
        // 搜索历史
        mHomeSearchHistoryRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new HistoryAdapter(mContext);
        mAdapter.setOnItemClickListener((position, value) -> {
            mHomeSearchContent.setText(value);
            search(value);
        });
        mHomeSearchHistoryRecycler.setAdapter(mAdapter);
        // 搜索文章
        mHomeSearchArticle.setLayoutManager(new LinearLayoutManager(mContext));
        mArticleAdapter = new ArticleAdapter(mContext);
        mHomeSearchArticle.setAdapter(mArticleAdapter);


        mHomeSearchBack.setOnClickListener(v -> {
            if (TextUtils.isEmpty(mHomeSearchContent.getText().toString().trim())) {
                finish();
            } else {
                mHomeSearchContent.setText("");
                mHomeSearchContent.clearFocus();
            }
        });
        mHomeSearchContent.requestFocus();
        mHomeSearchContent.setFocusableInTouchMode(true);

        // 点击键盘行的搜索后的结果
        mHomeSearchContent.setOnEditorActionListener((v, actionId, event) -> {
            String s = v.getText().toString().trim();
            if (actionId == EditorInfo.IME_ACTION_SEARCH && !TextUtils.isEmpty(s)) {
                search(v.getText().toString());
                return true;
            }
            return false;
        });
        // 搜索框为空时隐藏搜索界面
        mHomeSearchContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(mHomeSearchContent.getText())) {
                    mHomeSearchSearchContent.setVisibility(View.VISIBLE);
                    mHomeSearchArticle.setVisibility(View.GONE);
                    mHomeSearchContentClear.setVisibility(View.GONE);
                } else if (mHomeSearchContent.hasFocus()) {
                    mHomeSearchContentClear.setVisibility(View.VISIBLE);
                }
            }
        });
        // 搜索框失去焦点后不显示清除图标
        mHomeSearchContent.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && !TextUtils.isEmpty(mHomeSearchContent.getText().toString().trim())) {
                mHomeSearchContentClear.setVisibility(View.VISIBLE);
            } else {
                mHomeSearchContentClear.setVisibility(View.GONE);
            }
        });
        // 清空搜索内容
        mHomeSearchContentClear.setOnClickListener(v -> {
            mHomeSearchContent.clearFocus();
            mHomeSearchContent.setText("");
        });
        // 搜索
        mHomeSearchSearch.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(mHomeSearchContent.getText().toString().trim())) {
                search(mHomeSearchContent.getText().toString());
            }
        });

        // 下拉刷新和上拉加载
        mHomeSearchRefresh.setOnRefreshListener(refreshLayout -> search(searchContent));
        mHomeSearchRefresh.setOnLoadMoreListener(refreshLayout -> {
            this.page++;
            this.isLoadMore = true;
            mPresenter.searchArticle(page, searchContent);
        });
        // 收藏和取消收藏
        mArticleAdapter.setOnCollectClickListener((position, v, articleId, isCollect) -> {
            if (isCollect) {
                mPresenter.collectInside(position, v, articleId);
            } else {
                mPresenter.unCollect(position, v, articleId);
            }
        });
        mArticleAdapter.setOnItemClickListener(link -> {
            Intent intent = new Intent(mContext, WebActivity.class);
            intent.putExtra("link", link);
            mContext.startActivity(intent);
        });

        mPresenter.getHotKey();
        mPresenter.getHistory();
    }

    /**
     * 搜索，此时关闭键盘，并且清除焦点
     *
     * @param k 关键字
     */
    private void search(String k) {
        this.page = 0;
        this.searchContent = k;
        mPresenter.searchArticle(page, k);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        mHomeSearchContent.clearFocus();
    }

    @Override
    public void onBackPressed() {
        // 按下返回键后，若是搜索框不为空，则清空内容，否则返回home页
        if (TextUtils.isEmpty(mHomeSearchContent.getText().toString().trim())) {
            super.onBackPressed();
        } else {
            mHomeSearchContent.setText("");
            mHomeSearchContent.clearFocus();
        }
    }

    @Override
    public void collectSuccess(int position, View view) {

    }

    @Override
    public void collectFail(int position, View view) {
        Toast.makeText(mContext, "收藏失败！", Toast.LENGTH_SHORT).show();
        mArticleAdapter.setCollect(false, position, view);
    }

    @Override
    public void unCollectSuccess(int position, View view) {

    }

    @Override
    public void unCollectFail(int position, View view) {
        Toast.makeText(mContext, "取消收藏失败！", Toast.LENGTH_SHORT).show();
        mArticleAdapter.setCollect(true, position, view);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusUtils.unRegister(this);
    }

    @Subscribe
    public void onEvent(String message) {
        if (EventBusUtils.LOGIN_SUCCESS.equals(message)) {
            search(searchContent);
        }
    }
}
