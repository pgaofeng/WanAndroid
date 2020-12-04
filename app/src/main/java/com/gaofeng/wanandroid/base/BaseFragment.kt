package com.gaofeng.wanandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 *
 * @author 高峰
 * @date 2020/12/2 14:37
 * @desc Fragment基类
 */
abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(layoutRes(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView(savedInstanceState)
        observe()
        initData()
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