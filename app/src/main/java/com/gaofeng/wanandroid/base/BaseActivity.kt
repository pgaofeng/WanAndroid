package com.gaofeng.wanandroid.base

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.gaofeng.wanandroid.util.ActivityUtils


/**
 * @author 高峰
 * @date 202/12/6
 */
abstract class BaseActivity : AppCompatActivity() {

    /**
     * 标识当前Activity是否处于前台
     */
    protected var isActive: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityUtils.add(this)
        setView()
        initView(savedInstanceState == null)
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
     * Internal function, only used in base, do not override it.
     */
    open fun setView() {
        setContentView(layoutRes())
    }

    /**
     * 布局文件
     */
    @LayoutRes
    abstract fun layoutRes(): Int

    /**
     * 初始化布局View
     */
    open fun initView(isFirst: Boolean) {}

    /**
     * 与ViewModel中的数据进行绑定
     */
    open fun observe() {}

    /**
     * 初始化一些数据
     */
    open fun initData() {}

    /**
     * TODO 还需要寻找一个合适的设置颜色的方案
     */
    open fun setStatusBarColor(@ColorRes colorId: Int, dark: Boolean) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(colorId)
        if (!dark) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }
}