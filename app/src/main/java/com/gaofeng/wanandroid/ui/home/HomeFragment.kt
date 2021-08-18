package com.gaofeng.wanandroid.ui.home

import android.view.View
import android.widget.TextView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.gaofeng.wanandroid.R
import com.gaofeng.wanandroid.base.BaseFragment
import com.gaofeng.wanandroid.ui.home.fragment.ArticleFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * @author 高峰
 * @date 2020/12/2 14:36
 * @desc 首页Fragment
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private val mTitles by lazy { listOf("推荐", "广场", "问答") }
    private lateinit var mTabLayout: TabLayout
    private lateinit var mTvSearch: TextView
    private lateinit var mViewPager2: ViewPager2


    override fun getLayoutRes() = R.layout.fragment_home

    override fun initView(parentView: View) {
        super.initView(parentView)
        mTvSearch = parentView.findViewById(R.id.searchView)
        mTabLayout = parentView.findViewById(R.id.tabLayout)
        mViewPager2 = parentView.findViewById(R.id.viewPager)

        mViewPager2.adapter = object : FragmentStateAdapter(childFragmentManager, lifecycle) {
            override fun getItemCount() = mTitles.size

            override fun createFragment(position: Int) = ArticleFragment.create(position)

        }

        TabLayoutMediator(mTabLayout, mViewPager2) { tab, position ->
            tab.text = mTitles[position]
        }.attach()
    }

}