package com.example.wanandroid.mvp.me.presenter;

import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.TodoBean;
import com.example.wanandroid.mvp.me.contract.TodoContract;
import com.example.wanandroid.mvp.me.model.TodoModel;
import com.example.wanandroid.mvp.me.view.TodoFragment;
import com.example.wanandroid.network.ModelCallback;
import com.pgaofeng.common.base.BasePresenter;

import java.util.List;

/**
 * @author TodoPresenter
 * @date 2019/8/11
 * ${DESCRIPTION}
 */
public class TodoPresenter extends BasePresenter<TodoFragment, TodoModel> implements TodoContract.Presenter {


    public TodoPresenter(TodoFragment view) {
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
                mView.getTodoListSuccess((List<TodoBean>) baseData.getData());
            }

            @Override
            public void fail(Throwable throwable) {
                mView.getTodoListFail(throwable.getMessage());
            }
        });
    }

}
