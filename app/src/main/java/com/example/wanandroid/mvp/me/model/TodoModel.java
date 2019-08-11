package com.example.wanandroid.mvp.me.model;

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
                .subscribe(new BaseObserver<BaseResponse<List<TodoBean>>>(mDisposableManager) {
                    @Override
                    public void onSuccess(BaseResponse<List<TodoBean>> listBaseResponse) {
                        callback.success(listBaseResponse);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callback.fail(throwable);
                    }
                });
    }
}
