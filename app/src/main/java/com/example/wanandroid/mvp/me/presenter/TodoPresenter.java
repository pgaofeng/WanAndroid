package com.example.wanandroid.mvp.me.presenter;

import com.example.wanandroid.bean.BasePageBean;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.TodoBean;
import com.example.wanandroid.mvp.me.contract.TodoContract;
import com.example.wanandroid.mvp.me.model.TodoModel;
import com.example.wanandroid.network.ModelCallback;
import com.pgaofeng.common.base.BasePresenter;

import java.util.List;

/**
 * @author TodoPresenter
 * @date 2019/8/11
 * ${DESCRIPTION}
 */
public class TodoPresenter extends BasePresenter<TodoContract.View, TodoModel> implements TodoContract.Presenter {


    public TodoPresenter(TodoContract.View view) {
        super(view);
    }

    @Override
    protected TodoModel createModel() {
        return new TodoModel();
    }

    @Override
    public void getTodoList(int page, int status) {
        mModel.getTodoList(page, status, new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.getTodoListSuccess(((BasePageBean<List<TodoBean>>) baseData.getData()));
            }

            @Override
            public void fail(Throwable throwable) {
                mView.getTodoListFail(throwable.getMessage());
            }
        });
    }

    @Override
    public void addTodo(String title, String content, String date) {
        mModel.addTodo(title, content, date, new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.addTodoSuccess((TodoBean) baseData.getData());
            }

            @Override
            public void fail(Throwable throwable) {
                mView.onFail(throwable.getMessage());
            }
        });
    }

    @Override
    public void updateTodo(int id, String title, String content, String date, int position, int status) {
        mModel.updateTodo(id, title, content, date, status, new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.updateTodoSuccess(position, (TodoBean) baseData.getData());
            }

            @Override
            public void fail(Throwable throwable) {
                mView.onFail(throwable.getMessage());
            }
        });
    }

    @Override
    public void delTodo(int id, int position) {
        mModel.delTodo(id, new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.delTodoSuccess(position);
            }

            @Override
            public void fail(Throwable throwable) {
                mView.onFail(throwable.getMessage());
            }
        });
    }

    @Override
    public void updateTodoState(int id, int status, int position) {
        mModel.updateTodoState(id, status, new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.updateTodoSuccess(position, (TodoBean) baseData.getData());
            }

            @Override
            public void fail(Throwable throwable) {
                mView.onFail(throwable.getMessage());
            }
        });
    }

}
