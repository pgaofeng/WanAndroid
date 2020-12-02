package com.gaofeng.wanandroid.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 *
 * @author 高峰
 * @date 2020/11/25 11:45
 * @desc BaseActivity
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes())
        initView(savedInstanceState)
    }

    /**
     * 布局文件
     */
    @LayoutRes
    abstract fun layoutRes(): Int

    /**
     * 初始化布局View
     */
    open fun initView(savedInstanceState: Bundle?) {}
}