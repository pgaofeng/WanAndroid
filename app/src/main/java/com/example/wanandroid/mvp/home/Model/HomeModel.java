package com.example.wanandroid.mvp.home.Model;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.mvp.home.contract.HomeContract;
import com.example.wanandroid.network.ModelCallback;
import com.example.wanandroid.network.RetrofitClient;
import com.example.wanandroid.service.HomeService;
import com.pgaofeng.common.base.BaseModel;
import com.pgaofeng.common.network.BaseObserver;

import java.util.List;

/**
 * @author gaofengpeng
 * @date 2019/7/30
 * @description :
 */
public class HomeModel extends BaseModel implements HomeContract.Model {

    @Override
    public void getArticleList(int page, ModelCallback callBack) {
        RetrofitClient.getInstance()
                .createService(HomeService.class)
                .articleList(page)
                .compose(switchThread())
                .subscribe(new BaseObserver<BaseResponse<ArticleBean>>(mDisposableManager) {
                    @Override
                    public void onSuccess(BaseResponse<ArticleBean> articleBeanBaseResponse) {
                        callBack.success(articleBeanBaseResponse);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callBack.fail(throwable);
                    }
                });
    }

    @Override
    public void getTopArticleList(ModelCallback callback) {
        RetrofitClient.getInstance()
                .createService(HomeService.class)
                .topArticleList()
                .compose(switchThread())
                .subscribe(new BaseObserver<BaseResponse<List<ArticleBean.DatasBean>>>(mDisposableManager) {
                    @Override
                    public void onSuccess(BaseResponse<List<ArticleBean.DatasBean>> listBaseResponse) {
                        callback.success(listBaseResponse);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callback.fail(throwable);
                    }
                });
    }
}