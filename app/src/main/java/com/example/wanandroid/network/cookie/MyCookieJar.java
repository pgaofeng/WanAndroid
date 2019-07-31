package com.example.wanandroid.network.cookie;

import android.content.Context;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * @author gaofengpeng
 * @date 2019/7/31
 * @description :
 */
public class MyCookieJar implements CookieJar {

    private MyCookieStore store;

    public MyCookieJar(Context context) {
        store = new MyCookieStore(context);
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies == null || cookies.size() == 0) {
            return;
        }
        for (Cookie cookie : cookies) {
            store.saveCookie(url.host(), cookie);
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        return store.getCookie(url.host());
    }
}