package com.example.wanandroid.mvp.me.model;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.mvp.me.contract.CollectContract;
import com.example.wanandroid.network.ModelCallback;
import com.example.wanandroid.network.RetrofitClient;
import com.example.wanandroid.service.MeService;
import com.pgaofeng.common.base.BaseModel;
import com.pgaofeng.common.network.BaseObserver;

/**
 * @author gaofengpeng
 * @date 2019/8/6
 * @description :
 */
public class CollectModel extends BaseModel implements CollectContract.Model {
    @Override
    public void getCollectList(int page, ModelCallback callback) {
        RetrofitClient.getInstance()
                .createService(MeService.class)
                .getCollectList(page)
                .compose(switchThread())
                .subscribe(new BaseObserver<BaseResponse<ArticleBean>>(mDisposableManager) {
                    @Override
                    public void onSuccess(BaseResponse<ArticleBean> articleBeanBaseResponse) {
                        callback.success(articleBeanBaseResponse);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callback.fail(throwable);
                    }
                });
    }
}
