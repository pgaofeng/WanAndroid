package com.example.wanandroid.mvp.me.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.BasePageBean;
import com.example.wanandroid.bean.TodoBean;
import com.example.wanandroid.mvp.me.adapter.TodoAdapter;
import com.example.wanandroid.mvp.me.contract.TodoContract;
import com.example.wanandroid.mvp.me.presenter.TodoPresenter;
import com.example.wanandroid.util.CommonUtils;
import com.example.wanandroid.util.EventBusUtils;
import com.google.gson.Gson;
import com.pgaofeng.common.base.BaseFragment;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * @author TodoFragment
 * @date 2019/8/11
 * ${DESCRIPTION}
 */
public class TodoFragment extends BaseFragment<TodoPresenter> implements TodoContract.View {

    private int status = 0;
    int page = 1;
    private boolean isLoadMore = false;
    private TodoAdapter mAdapter;

    private RecyclerView mRecyclerView;
    private RefreshLayout mRefreshLayout;
    private FloatingActionButton mButton;

    @Override
    public void getTodoListSuccess(BasePageBean<List<TodoBean>> data) {
        if (data.isOver()) {
            mRefreshLayout.finishLoadMoreWithNoMoreData().finishRefresh();
        } else {
            mRefreshLayout.finishRefresh().finishLoadMore();
        }
        if (isLoadMore) {
            this.isLoadMore = false;
            mAdapter.addData(data.getDatas());
        } else {
            mAdapter.setData(data.getDatas());
        }
    }

    @Override
    public void getTodoListFail(String message) {
        mRefreshLayout.finishLoadMore(false).finishRefresh(false);
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addTodoSuccess(TodoBean bean) {

    }

    @Override
    public void delTodoSuccess(int position) {

    }

    @Override
    public void updateTodoSuccess(int position, TodoBean bean) {

    }

    @Override
    public void onFail(String message) {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_todo;
    }

    @Override
    protected void initView(View view) {
        status = getArguments().getInt("status");
        mRecyclerView = view.findViewById(R.id.me_todo_recycler);
        mRefreshLayout = view.findViewById(R.id.me_todo_refresh);
        mButton = view.findViewById(R.id.me_todo_add);
        if (status == 1) {
            mButton.hide();
        } else {
            mButton.show();
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new TodoAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);

        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            this.page = 1;
            mPresenter.getTodoList(page, status);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            this.page++;
            this.isLoadMore = true;
            mPresenter.getTodoList(page, status);
        });

        mButton.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, TodoAddActivity.class);
            mContext.startActivity(intent);
        });

        mAdapter.setOnItemClickListener((bean, position) -> {
            if (!CommonUtils.quickClick(800)) {
                String beanStr = new Gson().toJson(bean);
                Intent intent = new Intent(mContext, TodoAddActivity.class);
                intent.putExtra("TODOBEAN", beanStr);
                intent.putExtra("POSITION", position);
                intent.putExtra("status", status);
                mContext.startActivity(intent);
            }
        });

        EventBusUtils.register(this);
        mPresenter.getTodoList(page, status);
    }

    @Override
    protected TodoPresenter createPresenter() {
        return new TodoPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBusUtils.unRegister(this);
    }

    @Subscribe
    public void onEvent(Object object) {
        if (object instanceof TodoBean) {
            TodoBean bean = (TodoBean) object;
            int position = bean.getType();
            // 添加一个新的TODO
            if (position == -1) {
                mAdapter.addItem(bean);
                mRecyclerView.scrollToPosition(0);
                // 修改TODO
            } else {
                // 修改了状态为已完成(bean.getPriority()用于判断该item是哪里点击的，0则是待完成中的，1则是已完成中的)
                if (bean.getStatus() == 1 && bean.getPriority() == 0) {
                    mAdapter.remove(position);
                    // 修改其他内容
                } else {
                    mAdapter.replace(bean, position);
                }
            }
        }
    }
}
