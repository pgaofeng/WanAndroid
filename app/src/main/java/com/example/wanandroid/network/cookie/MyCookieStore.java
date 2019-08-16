package com.example.wanandroid.network.cookie;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Cookie;

/**
 * @author gaofengpeng
 * @date 2019/7/31
 * @description :Cookie的存储管理。在内存中和本地中各管理一份，采用的键值对形式。key为host@cookieName，值为Cookie的封装类PersistentCookie的持久化字符串
 */

public class MyCookieStore {

    public static final String COOKIE_STORE_NAME = "cookies_pref";
    private final String ENCODING = "ISO-8859-1";
    private Context context;
    private SharedPreferences preferences;//存放本地的Cookie
    private Map<String, Cookie> cookies;//存放内存中的cookie


    public MyCookieStore(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(COOKIE_STORE_NAME, Context.MODE_PRIVATE);
        cookies = new ConcurrentHashMap<>();

        //初始化时将所有的Cookie读取到内存中去
        Map<String, ?> persistent = preferences.getAll();
        for (Map.Entry<String, ?> entry : persistent.entrySet()) {
            Cookie cookie = decodeCookie((String) entry.getValue());
            cookies.put(entry.getKey(), cookie);
        }
    }

    /**
     * 将字符串解析为Cookie对象
     *
     * @param value PersistentCookie对应的字符串
     * @return 相应的Cookie
     */
    private Cookie decodeCookie(String value) {

        ObjectInputStream inputStream = null;
        PersistentCookie persistentCookie = null;
        try {
            inputStream = new ObjectInputStream(new ByteArrayInputStream(value.getBytes(ENCODING)));
            persistentCookie = (PersistentCookie) inputStream.readObject();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return persistentCookie.getCreateCookie();
    }

    /**
     * 将Cookie存入本地和内存中
     *
     * @param url    Cookie对应的host
     * @param cookie Cookie
     */
    public void saveCookie(String url, Cookie cookie) {
        String cookiesString = decodeCookieString(new PersistentCookie(cookie));
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(url + "@" + cookie.name(), cookiesString);
        editor.apply();
        cookies.put(url + "@" + cookie.name(), cookie);
    }

    /**
     * 将PersistentCookie解析为字符串
     *
     * @param cookie Cookie的封装类，实现Serializable接口
     * @return 准换的字符串
     */
    private String decodeCookieString(PersistentCookie cookie) {
        ObjectOutputStream out = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            out = new ObjectOutputStream(byteArrayOutputStream);
            out.writeObject(cookie);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = "";
        try {
            result = new String(byteArrayOutputStream.toByteArray(), ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据url从内存中获取相应的Cookie
     *
     * @param url host
     * @return Cookie的list集合
     */
    public List<Cookie> getCookie(String url) {
        List<Cookie> cookieList = new ArrayList<>();
        for (Map.Entry<String, Cookie> entry : cookies.entrySet()) {
            if (entry.getKey().contains(url))
                cookieList.add(entry.getValue());
        }
        return cookieList;
    }
}
