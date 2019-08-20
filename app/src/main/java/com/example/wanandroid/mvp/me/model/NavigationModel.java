package com.example.wanandroid.mvp.me.model;

import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.HeaderBean;
import com.example.wanandroid.bean.NavigationBean;
import com.example.wanandroid.mvp.me.contract.NavigationContract;
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
 * @date 2019/8/12
 * @description :
 */
public class NavigationModel extends BaseModel implements NavigationContract.Model {
    @Override
    public void getNavigation(ModelCallback callback) {
        RetrofitClient.getInstance()
                .createService(MeService.class)
                .getNavigation()
                .compose(switchThread())
                .map(naviList -> {
                    List<HeaderBean> headerBeans = new ArrayList<>();
                    for (NavigationBean bean : naviList.getData()) {
                        for (int i = 0; i < bean.getArticles().size(); i++) {
                            HeaderBean headerBean = new HeaderBean();
                            headerBean.setName(bean.getArticles().get(i).getTitle());
                            headerBean.setLink(bean.getArticles().get(i).getLink());
                            headerBean.setHeaderName(bean.getName());
                            headerBean.setHeader(i == 0);
                            headerBeans.add(headerBean);
                        }
                    }
                    Realm realm = Realm.getDefaultInstance();
                    realm.executeTransaction(realm1 -> {
                        realm.delete(HeaderBean.class);
                        realm.copyToRealm(headerBeans);
                    });
                    BaseResponse<List<HeaderBean>> response = new BaseResponse<>();
                    response.setData(headerBeans);
                    return response;
                })
                .subscribe(new BaseObserver<BaseResponse<List<HeaderBean>>>(mDisposableManager) {
                    @Override
                    public void onSuccess(BaseResponse<List<HeaderBean>> listBaseResponse) {
                        callback.success(listBaseResponse);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callback.fail(throwable);
                    }
                });
    }

    @Override
    public void getNaviCache(ModelCallback callback) {
        Observable.just(1)
                .map(integer -> {
                    Realm realm = Realm.getDefaultInstance();
                    RealmResults<HeaderBean> data = realm.where(HeaderBean.class).findAll();
                    BaseResponse<List<HeaderBean>> response = new BaseResponse<>();
                    response.setData(new ArrayList<>(data));
                    return response;
                })
                .subscribe(new BaseObserver<BaseResponse<List<HeaderBean>>>(mDisposableManager) {
                    @Override
                    public void onSuccess(BaseResponse<List<HeaderBean>> listBaseResponse) {
                        callback.success(listBaseResponse);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callback.fail(throwable);
                    }
                });
    }
}
