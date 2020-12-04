package com.gaofeng.wanandroid.screen

import android.os.Bundle
import androidx.fragment.app.commit
import com.gaofeng.wanandroid.R
import com.gaofeng.wanandroid.base.BaseActivity
import com.gaofeng.wanandroid.screen.answer.AnswerFragment
import com.gaofeng.wanandroid.screen.home.HomeFragment
import com.gaofeng.wanandroid.screen.me.MeFragment
import com.gaofeng.wanandroid.screen.square.SquareFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author 高峰
 * @date 2020/12/2
 *
 * 程序的主Activity
 */
@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun layoutRes() = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        bottomView.setOnNavigationItemSelectedListener {
            showFragment(it.itemId, first = false)
            true
        }
        bottomView.setOnNavigationItemReselectedListener {}
        if (savedInstanceState == null) {
            showFragment(R.id.home, first = true)
        }
    }

    /**
     * 显示Fragment，当first为true时，代表是第一次进入该界面
     */
    private fun showFragment(itemId: Int, first: Boolean) = supportFragmentManager.apply {
        val current = if (first) null else {
            findFragmentByTag(bottomView.selectedItemId.toString())
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