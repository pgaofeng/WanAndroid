package com.example.wanandroid;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;

/**
 * @author gaofengpeng
 * @date 2019/7/31
 * @description : Application
 */
public class App extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        Realm.init(this);
    }

    public static Context getContext() {
        return sContext;
    }
}
