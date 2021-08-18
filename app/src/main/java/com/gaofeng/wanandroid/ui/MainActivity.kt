package com.gaofeng.wanandroid.ui

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import com.gaofeng.wanandroid.R
import com.gaofeng.wanandroid.base.BaseActivity
import com.gaofeng.wanandroid.ui.answer.AnswerFragment
import com.gaofeng.wanandroid.ui.home.HomeFragment
import com.gaofeng.wanandroid.ui.me.MeFragment
import com.gaofeng.wanandroid.ui.square.SquareFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author gaofeng
 * @date 2020/12/2
 *
 * MainActivity，内部含有四个Fragment，分别用来对应四个tab
 */
@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var mBottomView: BottomNavigationView
    private lateinit var mContainer: FragmentContainerView

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        super.initView()
        mBottomView = findViewById(R.id.bottomView)
        mContainer = findViewById(R.id.frame_container)
        mBottomView.setOnItemSelectedListener {
            showFragment(it.itemId)
            true
        }

        // 不重写该方法的话，多次点击选中的item仍会触发onItemSelectedListener
        mBottomView.setOnItemReselectedListener {}
        showFragment(R.id.home)
    }

    /**
     * 根据Id去显示对应的Fragment
     */
    private fun showFragment(@IdRes itemId: Int) {
        supportFragmentManager.commit {
            supportFragmentManager.findFragmentByTag(mBottomView.selectedItemId.toString())?.also {
                hide(it)
            }
            val target = supportFragmentManager.findFragmentByTag(itemId.toString())
                ?: createFragment(itemId).also { add(R.id.frame_container, it, itemId.toString()) }
            show(target)
        }
    }

    /**
     * 根据选中的id去获取Fragment
     */
    private fun createFragment(@IdRes itemId: Int): Fragment = when (itemId) {
        R.id.home -> HomeFragment()
        R.id.answer -> AnswerFragment()
        R.id.square -> SquareFragment()
        else -> MeFragment()
    }
}