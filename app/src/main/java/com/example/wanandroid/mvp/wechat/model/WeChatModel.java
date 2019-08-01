package com.example.wanandroid.mvp.wechat.model;

import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.WeChatBean;
import com.example.wanandroid.mvp.wechat.contract.WeChatContract;
import com.example.wanandroid.network.ModelCallback;
import com.example.wanandroid.network.RetrofitClient;
import com.example.wanandroid.service.WeChatService;
import com.pgaofeng.common.base.BaseModel;
import com.pgaofeng.common.network.BaseObserver;

import java.util.List;

/**
 * @author gaofengpeng
 * @date 2019/8/1
 * ${DESCRIPTION}
 */
public class WeChatModel extends BaseModel implements WeChatContract.Model {

    @Override
    public void getWxList(ModelCallback callback) {
        RetrofitClient.getInstance()
                .createService(WeChatService.class)
                .getWxList()
                .compose(switchThread())
                .subscribe(new BaseObserver<BaseResponse<List<WeChatBean>>>(mDisposableManager) {
                    @Override
                    public void onSuccess(BaseResponse<List<WeChatBean>> listBaseResponse) {
                        callback.success(listBaseResponse);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callback.fail(throwable);
                    }
                });
    }
}
