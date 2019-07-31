package com.example.wanandroid.util;

import android.content.Context;

/**
 * @author gaofengpeng
 * @date 2019/7/31
 * @description : 屏幕工具
 */
public class ScreenUtils {

    /**
     * dp值转换成px值
     *
     * @param context 上下文
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
