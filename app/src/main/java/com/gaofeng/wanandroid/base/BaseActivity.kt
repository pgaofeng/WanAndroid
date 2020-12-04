package com.gaofeng.wanandroid.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.gaofeng.wanandroid.util.ActivityUtils

/**
 *
 * @author 高峰
 * @date 2020/11/25 11:45
 * @desc BaseActivity
 */
abstract class BaseActivity : AppCompatActivity() {

    /**
     * 标识当前Activity是否处于前台
     */
    protected var isActive: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityUtils.add(this)
        setContentView(layoutRes())
        initView(savedInstanceState)
        observe()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityUtils.remove(this)
    }

    override fun onResume() {
        super.onResume()
        isActive = true
    }

    override fun onPause() {
        super.onPause()
        isActive = false
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

    /**
     * 与ViewModel中的数据进行绑定
     */
    open fun observe() {}

    /**
     * 初始化一些数据
     */
    open fun initData() {}
}