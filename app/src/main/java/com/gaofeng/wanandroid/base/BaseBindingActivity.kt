package com.gaofeng.wanandroid.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 *
 * @author 高峰
 * @date 2020/11/25 11:45
 * @desc BaseBindingActivity
 */
abstract class BaseBindingActivity<B : ViewDataBinding> : AppCompatActivity() {

    abstract val layoutId: Int
    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        init()
    }

    /**
     * 初始化操作
     */
    open fun init() = Unit

}