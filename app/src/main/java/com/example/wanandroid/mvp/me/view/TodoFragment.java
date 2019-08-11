package com.example.wanandroid.mvp.me.view;

import android.view.View;

import com.example.wanandroid.bean.TodoBean;
import com.example.wanandroid.mvp.me.contract.TodoContract;
import com.example.wanandroid.mvp.me.presenter.TodoPresenter;
import com.pgaofeng.common.base.BaseFragment;

import java.util.List;

/**
 * @author TodoFragment
 * @date 2019/8/11
 * ${DESCRIPTION}
 */
public class TodoFragment extends BaseFragment<TodoPresenter> implements TodoContract.View {
    @Override
    public void getTodoListSuccess(List<TodoBean> data) {

    }

    @Override
    public void getTodoListFail(String message) {

    }

    @Override
    protected int getContentView() {
        return 0;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected TodoPresenter createPresenter() {
        return null;
    }
}
