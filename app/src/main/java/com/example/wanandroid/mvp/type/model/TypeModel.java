package com.example.wanandroid.mvp.type.model;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.TypeBean;
import com.example.wanandroid.mvp.type.contract.TypeContract;
import com.example.wanandroid.network.ModelCallback;
import com.example.wanandroid.network.RetrofitClient;
import com.example.wanandroid.service.TypeService;
import com.pgaofeng.common.base.BaseModel;
import com.pgaofeng.common.network.BaseObserver;

import java.util.List;

/**
 * @author gaofengpeng
 * @date 2019/7/31
 * @description :
 */
public class TypeModel extends BaseModel implements TypeContract.Model {
    @Override
    public void getTypeList(ModelCallback callback) {
        RetrofitClient.getInstance()
                .createService(TypeService.class)
                .getTypeList()
                .compose(switchThread())
                .subscribe(new BaseObserver<BaseResponse<List<TypeBean>>>(mDisposableManager) {
                    @Override
                    public void onSuccess(BaseResponse<List<TypeBean>> listBaseResponse) {
                        callback.success(listBaseResponse);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callback.fail(throwable);
                    }
                });
    }

    @Override
    public void getTypeArticle(int page, int cid, ModelCallback callback) {
        RetrofitClient.getInstance()
                .createService(TypeService.class)
                .getTypeArticle(page, cid)
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
