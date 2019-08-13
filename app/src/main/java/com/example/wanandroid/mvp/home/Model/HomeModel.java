package com.example.wanandroid.mvp.home.Model;

import com.example.wanandroid.App;
import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.UpdateBean;
import com.example.wanandroid.mvp.home.contract.HomeContract;
import com.example.wanandroid.network.ModelCallback;
import com.example.wanandroid.network.RetrofitClient;
import com.example.wanandroid.service.HomeService;
import com.pgaofeng.common.base.BaseModel;
import com.pgaofeng.common.network.BaseObserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    @Override
    public void update(HomeContract.DownLoadListener callback) {
        RetrofitClient.getInstance()
                .createService(HomeService.class)
                .update("https://raw.githubusercontent.com/pgaofeng/WanAndroid/master/update/release/app-release.apk")
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        //由于使用了@Streaming注解请求结果以流的形式返回，需要在异步线程中处理
                        //该方法处于主线程
                        new Thread(() -> {

                            ResponseBody responseBody = response.body();

                            //在外置存储下创建文件
                            //目录为外置存储的Android/data/com.xxx.xx/files根目录下
                            File file = new File(App.getContext().getExternalFilesDir(null) + File.separator + "玩Android.apk");

                            FileOutputStream out = null;
                            InputStream in = null;
                            try {
                                out = new FileOutputStream(file);
                                in = responseBody.byteStream();
                                byte[] buffer = new byte[1024 * 8];
                                long curr = 0;
                                long length = responseBody.contentLength();
                                int n = 0;
                                while ((n = in.read(buffer)) != -1) {
                                    curr += n;
                                    out.write(buffer, 0, n);
                                    ///    callback.downLoadProgress(curr, length);
                                }
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    in.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    out.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            callback.downLoadSuccess();

                        }).start();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        callback.downLoadFail(t.getMessage());
                    }
                });
    }

    @Override
    public void checkUpdate(ModelCallback callback) {
        RetrofitClient.getInstance()
                .createService(HomeService.class)
                .checkUpdate("https://raw.githubusercontent.com/pgaofeng/WanAndroid/master/update/release/output.json")
                .compose(switchThread())
                .subscribe(new BaseObserver<List<UpdateBean>>(mDisposableManager) {
                    @Override
                    public void onSuccess(List<UpdateBean> updateBeanlist) {
                        BaseResponse<UpdateBean> response = new BaseResponse<>();
                        response.setData(updateBeanlist.get(0));
                        callback.success(response);
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        callback.fail(throwable);
                    }
                });
    }
}
