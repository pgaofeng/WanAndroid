package com.gaofeng.wanandroid.screen.home

import android.view.View
import androidx.fragment.app.viewModels
import com.gaofeng.wanandroid.R
import com.gaofeng.wanandroid.base.BaseBindingFragment
import com.gaofeng.wanandroid.databinding.FragmentHomeBinding
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

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var adapter: HomeArticleAdapter

    override fun initView(view: View, isFirst: Boolean) {
        super.initView(view,isFirst)
        binding.banner.setUrl(
            listOf(
                "https://wanandroid.com/blogimgs/184b499f-dc69-41f1-b519-ff6cae530796.jpeg",
                "https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png",
                "https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png",
                "https://www.wanandroid.com/blogimgs/90c6cc12-742e-4c9f-b318-b912f163b8d0.png"
            )
        )
        adapter = HomeArticleAdapter(this)
        binding.recyclerView.adapter = adapter
        viewModel.articles.observe(this) { adapter.setData(it) }
    }

    override fun initData() {
        super.initData()
        viewModel.getTopArticle()
    }

}