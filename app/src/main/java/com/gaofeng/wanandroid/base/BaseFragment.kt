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
        mView = LayoutInflater.from(container?.context).inflate(getLayoutRes(), container, false)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(mView)
        initData()
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