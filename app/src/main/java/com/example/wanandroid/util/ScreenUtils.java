package com.example.wanandroid.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.wanandroid.R;

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

    /**
     * sp转dp
     *
     * @param context 上下文
     * @param spValue sp值
     * @return px值
     */
    public static int sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5);
    }

    /**
     * px 转sp
     *
     * @param context 上下文
     * @param pxValue px值
     * @return sp值
     */
    public static int px2sp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / scale + 0.5);
    }

    /**
     * 加载图片
     *
     * @param context   上下文
     * @param imageView ImageView
     * @param url       图片url
     */
    public static void loadImg(Context context, ImageView imageView, String url) {
        RequestOptions options = RequestOptions
                .noTransformation()
                .centerCrop()
                .placeholder(R.mipmap.ic_img_loading)
                .error(R.mipmap.ic_img_error)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(url).apply(options).into(imageView);
    }


}
