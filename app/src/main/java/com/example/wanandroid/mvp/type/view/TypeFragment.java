package com.example.wanandroid.mvp.type.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.TypeBean;
import com.example.wanandroid.mvp.type.adapter.TypeAdapter;
import com.example.wanandroid.mvp.type.contract.TypeContract;
import com.example.wanandroid.mvp.type.presenter.TypePresenter;
import com.pgaofeng.common.base.BaseFragment;

import java.util.List;

/**
 * @author TypeFragment
 * @date 2019/7/28
 * ${DESCRIPTION}
 */
public class TypeFragment extends BaseFragment<TypePresenter> implements TypeContract.View {

    private RecyclerView mRecyclerView;
    private TypeAdapter mAdapter;
    private static final String TAG = "TypeFragment";

    @Override
    public void getTypeListSuccess(List<TypeBean> datas) {
        Log.i(TAG, "getTypeListSuccess: " + datas.size());
        mAdapter.setDatas(datas);
    }

    @Override
    public void getTypeListFail(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getTypeArticleSuccess(ArticleBean bean) {

    }

    @Override
    public void getTypeArticleFail(String message) {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_type;
    }

    @Override
    protected void initView(View view) {
        mRecyclerView = view.findViewById(R.id.type_recycler);
        mAdapter = new TypeAdapter(mContext);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.getTypeList();
    }

    @Override
    protected TypePresenter createPresenter() {
        return new TypePresenter(this);
    }

    @Override
    public void collectSuccess(int position, View view) {

    }

    @Override
    public void collectFail(int position, View view) {

    }

    @Override
    public void unCollectSuccess(int position, View view) {

    }

    @Override
    public void unCollectFail(int position, View view) {

    }
}
