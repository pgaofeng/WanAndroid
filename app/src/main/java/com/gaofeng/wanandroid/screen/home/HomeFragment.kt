package com.gaofeng.wanandroid.screen.home

import android.view.View
import com.gaofeng.wanandroid.R
import com.gaofeng.wanandroid.base.BaseBindingFragment
import com.gaofeng.wanandroid.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * @author 高峰
 * @date 2020/12/2 14:36
 * @desc 首页Fragment
 */
@AndroidEntryPoint
class HomeFragment : BaseBindingFragment<FragmentHomeBinding>() {
    override fun layoutRes() = R.layout.fragment_home

    private val titles by lazy { listOf("推荐", "广场", "问答") }

    override fun initView(view: View, isFirst: Boolean) {
        super.initView(view, isFirst)
        binding.viewPager.adapter = HomeViewPagerAdapter(childFragmentManager, lifecycle, titles)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }

}