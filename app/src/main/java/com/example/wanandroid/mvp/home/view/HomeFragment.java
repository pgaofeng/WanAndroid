package com.example.wanandroid.mvp.home.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.wanandroid.App;
import com.example.wanandroid.R;
import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.mvp.home.adapter.ArticleAdapter;
import com.example.wanandroid.mvp.home.contract.HomeContract;
import com.example.wanandroid.mvp.home.presenter.HomePresenter;
import com.example.wanandroid.mvp.home.service.DownLoadService;
import com.example.wanandroid.util.EventBusUtils;
import com.pgaofeng.common.base.BaseFragment;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.List;

/**
 * @author HomeFragment
 * @date 2019/7/28
 * 首页
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {
    private static final String TAG = "HomeFragment";
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
    private Intent serviceIntent;

    @Override
    protected int getContentView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        serviceIntent = new Intent(mContext, DownLoadService.class);
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

        mAdapter.setOnCollectClickListener((position, v, articleId, isCollect) -> {
            if (isCollect) {
                mPresenter.collectInside(position, v, articleId);
            } else {
                mPresenter.unCollect(position, v, articleId);
            }
        });
        mPresenter.checkUpdate();
        mPresenter.getTopArticleList();
        mPresenter.getArticleList(page);
        EventBusUtils.register(this);
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
        mRefreshLayout.finishRefresh(false).finishLoadMore(false);
    }

    @Override
    public void getTopArticleListSuccess(List<ArticleBean.DatasBean> bean) {
        mAdapter.setDatas(null, bean);
    }

    @Override
    public void getTopArticleListFail(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        mRefreshLayout.finishRefresh(false).finishLoadMore(false);
    }

    @Override
    public void startUpdate() {
        Toast.makeText(mContext, "开始下载", Toast.LENGTH_SHORT).show();
        mContext.startService(serviceIntent);
    }

    @Override
    public void updateProgress(long cur, long total) {
        System.out.println(cur + "/" + total);
    }

    @Override
    public void updateSuccess() {
        Toast.makeText(mContext, "下载成功", Toast.LENGTH_SHORT).show();
        mContext.stopService(serviceIntent);
        File file = new File(App.getContext().getExternalFilesDir(null) + File.separator + "玩Android.apk");
        installApk(file);
    }


    //安装apk，兼容7.0
    protected void installApk(File file) {
        if (!file.exists()) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        // 由于没有在Activity环境下启动Activity,设置下面的标签   setFlags要放在addFlags之前
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //版本在7.0以上是不能直接通过uri访问的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //参数1 上下文, 参数2 Provider主机地址和清单文件中保持一致   参数3 共享的文件
            Uri apkUri =
                    FileProvider.getUriForFile(mContext, "com.example.wanandroid.fileProvider", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file),
                    "application/vnd.android.package-archive");
        }
        mContext.startActivity(intent);
    }


    @Override
    public void updateFail(String message) {
        Toast.makeText(mContext, "下载失败：" + message, Toast.LENGTH_SHORT).show();
        mContext.stopService(serviceIntent);
    }

    @Override
    public void hasUpdate() {

        new AlertDialog.Builder(mContext)
                .setTitle("更新")
                .setMessage("是否更新？")
                .setCancelable(false)
                .setNegativeButton("取消", (dialog1, which) -> dialog1.dismiss())
                .setPositiveButton("确定", (dialog1, which) -> mPresenter.update())
                .create()
                .show();
    }

    @Override
    public void noUpdate() {
//        Toast.makeText(mContext, "没有更新", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "noUpdate: 没有更新");
    }

    @Override
    public void checkFail(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
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
