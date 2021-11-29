package com.gaofeng.wanandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 *
 * @author 高峰
 * @date 2020/12/2 14:37
 * @desc Fragment基类
 */
abstract class BaseBindingFragment<B : ViewDataBinding> : Fragment() {

    protected lateinit var binding: B
    protected lateinit var mRootView: View
    abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        mRootView = binding.root
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    /**
     * 初始化操作
     */
    open fun init() = Unit
}