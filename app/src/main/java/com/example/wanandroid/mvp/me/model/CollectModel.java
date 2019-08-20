package com.example.wanandroid.mvp.me.model;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.CollectWebsiteBean;
import com.example.wanandroid.bean.DatasBean;
import com.example.wanandroid.mvp.me.contract.CollectContract;
import com.example.wanandroid.network.BaseObserver;
import com.example.wanandroid.network.ModelCallback;
import com.example.wanandroid.network.RetrofitClient;
import com.example.wanandroid.service.MeService;
import com.pgaofeng.common.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;

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
                .map(dataList -> {
                    if (page != 0)
                        return dataList;
                    Realm realm = Realm.getDefaultInstance();
                    realm.executeTransaction(realm1 -> {
                        RealmResults<DatasBean> datasBeans = realm.where(DatasBean.class).equalTo("articleType", 2).findAll();
                        for (DatasBean bean : datasBeans) {
                            bean.deleteFromRealm();
                        }
                        for (DatasBean bean : dataList.getData().getDatas()) {
                            bean.setArticleType(2);
                            realm.copyToRealm(bean);
                        }
                    });
                    return dataList;
                })
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

    @Override
    public void getCollectWebsite(ModelCallback callback) {
        RetrofitClient.getInstance()
                .createService(MeService.class)
                .getCollectWebsite()
                .compose(switchThread())
                .map(dataList -> {
                    Realm realm = Realm.getDefaultInstance();
                    realm.executeTransaction(realm1 -> {
                        RealmResults<CollectWebsiteBean> collectWebsiteBeans = realm.where(CollectWebsiteBean.class)
                                .equalTo("type", 0).findAll();
                        for (CollectWebsiteBean bean : collectWebsiteBeans) {
                            bean.deleteFromRealm();
                        }
                        for (CollectWebsiteBean bean : dataList.getData()) {
                            bean.setType(0);
                            realm.copyToRealm(bean);
                        }
                    });
                    return dataList;
                })
                .subscribe(new BaseObserver<BaseResponse<List<CollectWebsiteBean>>>(mDisposableManager) {
                    @Override
                    public void onSuccess(BaseResponse<List<CollectWebsiteBean>> listBaseResponse) {
                        callback.success(listBaseResponse);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callback.fail(throwable);
                    }
                });
    }

    @Override
    public void getCollectListCache(ModelCallback callBack) {
        Observable.just(1)
                .map(integer -> {
                    Realm realm = Realm.getDefaultInstance();
                    RealmResults<DatasBean> datasBeans = realm.where(DatasBean.class).equalTo("articleType", 2).findAll();
                    BaseResponse<List<DatasBean>> response = new BaseResponse<>();
                    response.setData(new ArrayList<>(datasBeans));
                    return response;
                })
                .subscribe(new BaseObserver<BaseResponse<List<DatasBean>>>(mDisposableManager) {
                    @Override
                    public void onSuccess(BaseResponse<List<DatasBean>> listBaseResponse) {
                        callBack.success(listBaseResponse);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callBack.fail(throwable);
                    }
                });
    }

    @Override
    public void getCollectWebCache(ModelCallback callback) {
        Observable.just(1)
                .map(integer -> {
                    Realm realm = Realm.getDefaultInstance();
                    RealmResults<CollectWebsiteBean> collectWebsiteBeans = realm.where(CollectWebsiteBean.class)
                            .equalTo("type", 0).findAll();
                    BaseResponse<List<CollectWebsiteBean>> response = new BaseResponse<>();
                    response.setData(new ArrayList<>(collectWebsiteBeans));
                    return response;
                })
                .subscribe(new BaseObserver<BaseResponse<List<CollectWebsiteBean>>>(mDisposableManager) {
                    @Override
                    public void onSuccess(BaseResponse<List<CollectWebsiteBean>> listBaseResponse) {
                        callback.success(listBaseResponse);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callback.fail(throwable);
                    }
                });
    }
}
