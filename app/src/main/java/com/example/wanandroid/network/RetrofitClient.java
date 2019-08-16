package com.example.wanandroid.network;

import android.util.SparseArray;

import com.example.wanandroid.App;
import com.example.wanandroid.network.cookie.MyCookieJar;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author gaofengpeng
 * @date 2019/7/30
 * @description : Retrofit实例
 */
public class RetrofitClient {
    private static RetrofitClient mRetrofitClient;

    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;

    private SparseArray<Object> mSparseArray;

    private RetrofitClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .cookieJar(new MyCookieJar(App.getContext()))
                .build();


        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://wanandroid.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build();

        mSparseArray = new SparseArray<>();

    }

    /**
     * 获取Retrofit实例
     *
     * @return Retrofit实例
     */
    public static RetrofitClient getInstance() {
        if (mRetrofitClient == null) {
            synchronized (RetrofitClient.class) {
                if (mRetrofitClient == null) {
                    mRetrofitClient = new RetrofitClient();
                }
            }
        }
        return mRetrofitClient;
    }

    /**
     * 创建对应的Service
     *
     * @param tClass Service类型
     * @param <T>    类型
     * @return Service
     */
    public <T> T createService(Class<T> tClass) {
        int hash = tClass.hashCode();
        Object object = mSparseArray.get(hash);
        if (object != null)
            return (T) object;
        T t = mRetrofit.create(tClass);
        mSparseArray.put(hash, t);
        return t;
    }
}
