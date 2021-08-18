package com.gaofeng.wanandroid.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 *
 * @author 高峰
 * @date 2020/11/25 11:45
 * @desc BaseBindingActivity
 */
abstract class BaseBindingActivity<B : ViewDataBinding> : BaseActivity() {

    protected lateinit var binding: B

    override fun setContentView() {
        binding = DataBindingUtil.setContentView(this, getLayoutRes())
    }

}