package com.gaofeng.wanandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment


/**
 * @author 高峰
 * @date 2020/12/6
 */
abstract class BaseFragment : Fragment() {

    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = createView(inflater, container)
        return mView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView(mView, savedInstanceState == null)
        observe()
        initData()
    }

    open fun createView(inflater: LayoutInflater, container: ViewGroup?): View =
        inflater.inflate(layoutRes(), container, false)

    /**
     * 布局文件
     */
    @LayoutRes
    abstract fun layoutRes(): Int

    /**
     * 初始化布局View
     */
    open fun initView(view: View, isFirst: Boolean) {}

    /**
     * 与ViewModel中的数据进行绑定
     */
    open fun observe() {}

    /**
     * 初始化一些数据
     */
    open fun initData() {}
}