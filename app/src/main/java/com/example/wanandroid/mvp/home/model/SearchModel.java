package com.example.wanandroid.mvp.home.model;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.HotKeyBean;
import com.example.wanandroid.mvp.home.contract.SearchContract;
import com.example.wanandroid.network.BaseObserver;
import com.example.wanandroid.network.ModelCallback;
import com.example.wanandroid.network.RetrofitClient;
import com.example.wanandroid.service.HomeService;
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
public class SearchModel extends BaseModel implements SearchContract.Model {
    @Override
    public void getHotKey(ModelCallback callback) {
        RetrofitClient.getInstance()
                .createService(HomeService.class)
                .getHotKey()
                .compose(switchThread())
                .map(hotkeyList -> {
                    Realm realm = Realm.getDefaultInstance();
                    realm.executeTransaction(realm1 -> {
                        RealmResults<HotKeyBean> data = realm.where(HotKeyBean.class).findAll();
                        for (HotKeyBean bean : data) {
                            bean.deleteFromRealm();
                        }
                        realm.copyToRealm(hotkeyList.getData());
                    });
                    return hotkeyList;
                })
                .subscribe(new BaseObserver<BaseResponse<List<HotKeyBean>>>(mDisposableManager) {
                    @Override
                    public void onSuccess(BaseResponse<List<HotKeyBean>> listBaseResponse) {
                        callback.success(listBaseResponse);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callback.fail(throwable);
                    }
                });
    }

    @Override
    public void getHistory(ModelCallback callback) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("测试" + i);
        }
        BaseResponse<List<String>> response = new BaseResponse<>();
        response.setData(list);
        callback.success(response);
    }

    @Override
    public void searchArticle(int page, String key, ModelCallback callback) {
        RetrofitClient.getInstance()
                .createService(HomeService.class)
                .search(page, key)
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

    @Override
    public void hotKeyFromCache(ModelCallback callback) {
        Observable.just(1)
                .map(integer -> {
                    Realm realm = Realm.getDefaultInstance();
                    RealmResults<HotKeyBean> datas = realm.where(HotKeyBean.class).findAll();
                    BaseResponse<List<HotKeyBean>> response = new BaseResponse<>();

                    response.setData(new ArrayList<>(datas));
                    return response;
                })
                .subscribe(new com.pgaofeng.common.network.BaseObserver<BaseResponse<List<HotKeyBean>>>(mDisposableManager) {
                    @Override
                    public void onSuccess(BaseResponse<List<HotKeyBean>> listBaseResponse) {
                        callback.success(listBaseResponse);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callback.fail(throwable);
                    }
                });
    }
}
