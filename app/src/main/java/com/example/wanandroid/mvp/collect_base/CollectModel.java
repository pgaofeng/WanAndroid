package com.example.wanandroid.mvp.collect_base;

import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.network.ModelCallback;
import com.example.wanandroid.network.RetrofitClient;
import com.example.wanandroid.service.CollectService;
import com.pgaofeng.common.network.BaseObserver;
import com.pgaofeng.common.network.DisposableManager;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author gaofengpeng
 * @date 2019/8/9
 * @description : 收藏相关Model
 */
public class CollectModel {
    /**
     * 收藏站内文章
     *
     * @param articleId 文章ID
     */
    public static void collectInside(int articleId, DisposableManager manager, ModelCallback callback) {
        RetrofitClient.getInstance()
                .createService(CollectService.class)
                .collectInsideArticle(articleId)
                .compose(switchThread())
                .subscribe(new BaseObserver<BaseResponse>(manager) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        callback.success(baseResponse);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callback.fail(throwable);
                    }
                });
    }

    /**
     * 收藏站外文章
     *
     * @param author 作者
     * @param title  标题
     * @param link   链接
     */
    public static void collectOutside(String author, String title, String link, DisposableManager manager, ModelCallback callback) {
        RetrofitClient.getInstance()
                .createService(CollectService.class)
                .collectOutsideArticle(author, title, link)
                .compose(switchThread())
                .subscribe(new BaseObserver<BaseResponse>(manager) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        callback.success(baseResponse);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callback.fail(throwable);
                    }
                });
    }

    /**
     * 取消收藏
     *
     * @param articleId 文章ID
     */
    public static void unCollectArticle(int articleId, DisposableManager manager, ModelCallback callback) {
        RetrofitClient.getInstance()
                .createService(CollectService.class)
                .unCollectArticle(articleId)
                .compose(switchThread())
                .subscribe(new BaseObserver<BaseResponse>(manager) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        callback.success(baseResponse);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callback.fail(throwable);
                    }
                });
    }

    /**
     * 从收藏列表取消收藏
     *
     * @param articleId 收藏文章ID
     * @param originId  文章所属分类ID
     */
    public static void unCollectArticleFromCollect(int articleId, int originId, DisposableManager manager, ModelCallback callback) {
        RetrofitClient.getInstance()
                .createService(CollectService.class)
                .unCollectArticleFromCollect(articleId, originId)
                .compose(switchThread())
                .subscribe(new BaseObserver<BaseResponse>(manager) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        callback.success(baseResponse);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callback.fail(throwable);
                    }
                });
    }


    /**
     * 数据请求切换线程，io线程请求数据，请求完后在主线程进行操作
     *
     * @param <T> 泛型，应当是BaseData<?>
     * @return 转换后的结果
     */
    public static <T> ObservableTransformer<T, T> switchThread() {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
    }
}
