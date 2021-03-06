package com.gaofeng.wanandroid.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 *
 * @author 高峰
 * @date 2020/12/2 14:37
 * @desc Fragment基类
 */
abstract class BaseBindingFragment<B : ViewDataBinding> : BaseFragment() {

    protected lateinit var binding: B

    override fun createView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = DataBindingUtil.inflate(inflater, layoutRes(), container, false)
        return binding.root
    }
}