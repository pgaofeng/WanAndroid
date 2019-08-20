package com.example.wanandroid.mvp.me.model;

import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.CollectWebsiteBean;
import com.example.wanandroid.mvp.me.contract.CommonlyContract;
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
 * @date 2019/8/9
 * @description :
 */
public class CommonlyModel extends BaseModel implements CommonlyContract.Model {
    @Override
    public void getCommonlyWebsite(ModelCallback callback) {
        RetrofitClient.getInstance()
                .createService(MeService.class)
                .getCommonlyWebsite()
                .compose(switchThread())
                .map(webList -> {
                    Realm realm = Realm.getDefaultInstance();
                    realm.executeTransaction(realm1 -> {
                        RealmResults<CollectWebsiteBean> collectWebsiteBeans = realm.where(CollectWebsiteBean.class)
                                .equalTo("type", 1).findAll();
                        for (CollectWebsiteBean bean : collectWebsiteBeans) {
                            bean.deleteFromRealm();
                        }
                        for (CollectWebsiteBean bean : webList.getData()) {
                            bean.setType(1);
                            realm.copyToRealm(bean);
                        }
                    });
                    return webList;
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
    public void getCommonlyCache(ModelCallback callback) {
        Observable.just(1)
                .map(integer -> {
                    Realm realm = Realm.getDefaultInstance();
                    RealmResults<CollectWebsiteBean> data = realm.where(CollectWebsiteBean.class).equalTo("type", 1).findAll();
                    BaseResponse<List<CollectWebsiteBean>> response = new BaseResponse<>();
                    response.setData(new ArrayList<>(data));
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
