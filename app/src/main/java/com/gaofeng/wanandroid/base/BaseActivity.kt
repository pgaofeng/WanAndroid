package com.gaofeng.wanandroid.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


/**
 * @author gaofengpeng
 * @date 2021/8/17
 */
abstract class BaseActivity : AppCompatActivity() {

    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        init()
    }

    /**
     * 初始化操作
     */
    open fun init() = Unit
}