package com.example.wanandroid.util;

import org.greenrobot.eventbus.EventBus;

/**
 * @author gaofengpeng
 * @date 2019/8/12
 * @description :
 */
public class EventBusUtils {


    public static void register(Object object) {
        EventBus.getDefault().register(object);
    }

    public static void unRegister(Object object) {
        EventBus.getDefault().unregister(object);
    }

    public static void sendMessage(String message) {
        EventBus.getDefault().post(message);
    }

    public static final String LOGIN_SUCCESS = "login_success";

}
