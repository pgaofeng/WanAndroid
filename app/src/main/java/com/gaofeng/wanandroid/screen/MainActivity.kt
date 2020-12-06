package com.gaofeng.wanandroid.screen

import androidx.fragment.app.commit
import com.gaofeng.wanandroid.R
import com.gaofeng.wanandroid.base.BaseBindingActivity
import com.gaofeng.wanandroid.databinding.ActivityMainBinding
import com.gaofeng.wanandroid.screen.answer.AnswerFragment
import com.gaofeng.wanandroid.screen.home.HomeFragment
import com.gaofeng.wanandroid.screen.me.MeFragment
import com.gaofeng.wanandroid.screen.square.SquareFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author 高峰
 * @date 2020/12/2
 *
 * 程序的主Activity
 */
@AndroidEntryPoint
class MainActivity : BaseBindingActivity<ActivityMainBinding>() {

    override fun layoutRes() = R.layout.activity_main

    override fun initView(isFirst: Boolean) {
        super.initView(isFirst)
        binding.bottomView.setOnNavigationItemSelectedListener {
            showFragment(it.itemId, first = false)
            true
        }
        binding.bottomView.setOnNavigationItemReselectedListener {}
        if (isFirst) {
            showFragment(R.id.home, first = true)
        }
    }

    /**
     * 显示Fragment，当first为true时，代表是第一次进入该界面
     */
    private fun showFragment(itemId: Int, first: Boolean) = supportFragmentManager.apply {
        val current = if (first) null else {
            findFragmentByTag(binding.bottomView.selectedItemId.toString())
        }
        commit {
            current?.also {
                hide(it)
            }
            val target = findFragmentByTag(itemId.toString()) ?: createFragment(itemId).also {
                add(R.id.frame_container, it, itemId.toString())
            }
            show(target)
        }
    }

    /**
     * 根据选中的id去获取Fragment
     */
    private fun createFragment(itemId: Int) = when (itemId) {
        R.id.home -> HomeFragment()
        R.id.answer -> AnswerFragment()
        R.id.square -> SquareFragment()
        else -> MeFragment()
    }
}