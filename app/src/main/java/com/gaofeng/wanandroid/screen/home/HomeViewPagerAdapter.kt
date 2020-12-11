package com.gaofeng.wanandroid.screen.home

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gaofeng.wanandroid.screen.home.fragment.ArticleFragment


/**
 *
 * @author 高峰
 * @date 2020/12/11 13:58
 * @desc Home页ViewPager2的adapter
 */
class HomeViewPagerAdapter(manager:FragmentManager,lifecycle: Lifecycle, private val title: List<String>) :
    FragmentStateAdapter(manager,lifecycle) {

    init {
        println("titles size : ${title.size}")
    }

    override fun getItemCount() = title.size

    override fun createFragment(position: Int) = ArticleFragment.create(position)
}