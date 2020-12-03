package com.gaofeng.wanandroid.screen.home

import com.gaofeng.wanandroid.R
import com.gaofeng.wanandroid.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *
 * @author 高峰
 * @date 2020/12/2 14:36
 * @desc 首页Fragment
 */
class HomeFragment : BaseFragment() {
    override fun layoutRes() = R.layout.fragment_home

    override fun initView() {
        super.initView()
        banner.setUrl(listOf(
            "https://wanandroid.com/blogimgs/184b499f-dc69-41f1-b519-ff6cae530796.jpeg",
            "https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png",
            "https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png",
            "https://www.wanandroid.com/blogimgs/90c6cc12-742e-4c9f-b318-b912f163b8d0.png"
        ))

        banner.postDelayed({
            banner.setUrl(listOf(
                "https://wanandroid.com/blogimgs/184b499f-dc69-41f1-b519-ff6cae530796.jpeg",
               // "https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png",
                //"https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png",
                "https://www.wanandroid.com/blogimgs/90c6cc12-742e-4c9f-b318-b912f163b8d0.png"
            ))
        },10000)
    }

}