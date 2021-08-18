package com.gaofeng.wanandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment


/**
 * @author gaofengpeng
 * @date 2021/8/17
 */
abstract class BaseFragment : Fragment() {

    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = setContentView(inflater, container)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(mView)
        initData()
    }

    /**
     * 设置布局View，抽出这一方法是为了后面适配DataBinding
     */
    open fun setContentView(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(getLayoutRes(), container, false)
    }

    /**
     * 布局文件
     */
    @LayoutRes
    abstract fun getLayoutRes(): Int

    /**
     * 初始化布局View
     */
    open fun initView(parentView: View) {}

    /**
     * 初始化一些数据
     */
    open fun initData() {}
}