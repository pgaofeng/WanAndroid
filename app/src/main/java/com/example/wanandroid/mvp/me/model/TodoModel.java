package com.example.wanandroid.mvp.me.model;

import com.example.wanandroid.bean.BasePageBean;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.TodoBean;
import com.example.wanandroid.mvp.me.contract.TodoContract;
import com.example.wanandroid.network.ModelCallback;
import com.example.wanandroid.network.RetrofitClient;
import com.example.wanandroid.service.MeService;
import com.pgaofeng.common.base.BaseModel;
import com.pgaofeng.common.network.BaseObserver;

import java.util.List;

/**
 * @author TodoModel
 * @date 2019/8/11
 * ${DESCRIPTION}
 */
public class TodoModel extends BaseModel implements TodoContract.Model {
    @Override
    public void getTodoList(int page, int status, ModelCallback callback) {
        RetrofitClient.getInstance()
                .createService(MeService.class)
                .getTodoList(page, status)
                .compose(switchThread())
                .subscribe(new BaseObserver<BaseResponse<BasePageBean<List<TodoBean>>>>(mDisposableManager) {
                    @Override
                    public void onSuccess(BaseResponse<BasePageBean<List<TodoBean>>> basePageBeanBaseResponse) {
                        callback.success(basePageBeanBaseResponse);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callback.fail(throwable);
                    }
                });
    }

    @Override
    public void addTodo(String title, String content, String date, ModelCallback callback) {
        RetrofitClient.getInstance()
                .createService(MeService.class)
                .addTodo(title, content, date)
                .compose(switchThread())
                .subscribe(new BaseObserver<BaseResponse<TodoBean>>(mDisposableManager) {
                    @Override
                    public void onSuccess(BaseResponse<TodoBean> todoBeanBaseResponse) {
                        callback.success(todoBeanBaseResponse);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callback.fail(throwable);
                    }
                });
    }

    @Override
    public void updateTodo(int id, String title, String content, String date, int status, ModelCallback callback) {
        RetrofitClient.getInstance()
                .createService(MeService.class)
                .updateTodo(id, title, content, date, status)
                .compose(switchThread())
                .subscribe(new BaseObserver<BaseResponse>(mDisposableManager) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        callback.success(baseResponse);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callback.fail(throwable);
                    }
                });
    }

    @Override
    public void delTodo(int id, ModelCallback callback) {
        RetrofitClient.getInstance()
                .createService(MeService.class)
                .delTodo(id)
                .compose(switchThread())
                .subscribe(new BaseObserver<BaseResponse>(mDisposableManager) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        callback.success(baseResponse);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callback.fail(throwable);
                    }
                });
    }

    @Override
    public void updateTodoState(int id, int status, ModelCallback callback) {
        RetrofitClient.getInstance()
                .createService(MeService.class)
                .doneTodo(id, status)
                .compose(switchThread())
                .subscribe(new BaseObserver<BaseResponse>(mDisposableManager) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        callback.success(baseResponse);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callback.fail(throwable);
                    }
                });
    }
}
