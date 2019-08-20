package com.example.wanandroid.mvp.wechat.model;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.DatasBean;
import com.example.wanandroid.bean.WeChatBean;
import com.example.wanandroid.mvp.wechat.contract.WeChatArticleContract;
import com.example.wanandroid.mvp.wechat.contract.WeChatContract;
import com.example.wanandroid.network.BaseObserver;
import com.example.wanandroid.network.ModelCallback;
import com.example.wanandroid.network.RetrofitClient;
import com.example.wanandroid.service.WeChatService;
import com.pgaofeng.common.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * @author gaofengpeng
 * @date 2019/8/1
 * ${DESCRIPTION}
 */
public class WeChatModel extends BaseModel implements WeChatContract.Model, WeChatArticleContract.Model {

    @Override
    public void getWxList(ModelCallback callback) {
        RetrofitClient.getInstance()
                .createService(WeChatService.class)
                .getWxList()
                .compose(switchThread())
                .map(wechatList -> {
                    Realm realm = Realm.getDefaultInstance();
                    realm.executeTransaction(realm1 -> {
                        realm.delete(WeChatBean.class);
                        realm.copyToRealm(wechatList.getData());
                    });
                    return wechatList;
                })
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

    @Override
    public void getWxListCache(ModelCallback callback) {
        Observable.just(1)
                .map(integer -> {
                    Realm realm = Realm.getDefaultInstance();
                    RealmResults<WeChatBean> data = realm.where(WeChatBean.class).findAll();
                    BaseResponse<List<WeChatBean>> baseResponse = new BaseResponse<>();
                    baseResponse.setData(new ArrayList<>(data));
                    return baseResponse;
                })
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

    @Override
    public void getArticleList(int id, int page, ModelCallback callback) {
        RetrofitClient.getInstance()
                .createService(WeChatService.class)
                .getWxArticleList(id, page)
                .compose(switchThread())
                .map(dataList -> {
                    if (page != 0)
                        return dataList;
                    Realm realm = Realm.getDefaultInstance();
                    realm.executeTransaction(realm1 -> {
                        RealmResults<DatasBean> datasBeans = realm.where(DatasBean.class).equalTo("articleType", id).findAll();
                        for (DatasBean bean : datasBeans) {
                            bean.deleteFromRealm();
                        }
                        for (DatasBean bean : dataList.getData().getDatas()) {
                            bean.setArticleType(id);
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
    public void getArticleCache(int id, ModelCallback callback) {
        Observable.just(1)
                .map(integer -> {
                    Realm realm = Realm.getDefaultInstance();
                    RealmResults<DatasBean> datasBeans = realm.where(DatasBean.class).equalTo("articleType", id).findAll();
                    BaseResponse<List<DatasBean>> response = new BaseResponse<>();
                    response.setData(new ArrayList<>(datasBeans));
                    return response;
                })
                .subscribe(new BaseObserver<BaseResponse<List<DatasBean>>>(mDisposableManager) {
                    @Override
                    public void onSuccess(BaseResponse<List<DatasBean>> listBaseResponse) {
                        callback.success(listBaseResponse);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callback.fail(throwable);
                    }
                });
    }
}
