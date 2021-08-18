package com.gaofeng.wanandroid.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity


/**
 * @author gaofengpeng
 * @date 2021/8/17
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView()
        initView()
        initData()
    }

    /**
     * 设置布局View，抽出这一方法是为了后面适配DataBinding
     */
    open fun setContentView() {
        setContentView(getLayoutRes())
    }

    /**
     * 获取Activity的布局文件
     */
    @LayoutRes
    abstract fun getLayoutRes(): Int

    /**
     * 开始请求，该方法用于初始化时的数据请求。
     */
    open fun initData() {}

    /**
     * 初始化View
     */
    open fun initView() {}
}