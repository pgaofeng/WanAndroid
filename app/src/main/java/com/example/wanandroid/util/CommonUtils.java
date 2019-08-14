package com.example.wanandroid.util;

/**
 * @author gaofengpeng
 * @date 2019/8/14
 * @description :
 */
public class CommonUtils {

    private static long lastTime = 0;


    /**
     * 快速点击判断
     *
     * @param ms 连续点击时间差
     * @return true 在ms秒之内的点击
     */
    public static boolean quickClick(long ms) {
        long curTime = System.currentTimeMillis();
        if (curTime - lastTime > ms) {
            lastTime = curTime;
            return false;
        }
        return true;
    }

}
