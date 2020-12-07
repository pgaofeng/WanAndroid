package com.gaofeng.wanandroid.screen.home

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import com.gaofeng.wanandroid.R
import com.gaofeng.wanandroid.base.BaseBindingFragment
import com.gaofeng.wanandroid.common.CommonFooterAdapter
import com.gaofeng.wanandroid.databinding.FragmentHomeBinding
import com.gaofeng.wanandroid.screen.home.adapter.BannerAdapter
import com.gaofeng.wanandroid.screen.home.adapter.MainArticleAdapter
import com.gaofeng.wanandroid.screen.home.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 *
 * @author 高峰
 * @date 2020/12/2 14:36
 * @desc 首页Fragment，首页主要是一个RecyclerView，分了三个adapter，分别是banner，置顶文章，普通文章
 */
@AndroidEntryPoint
class HomeFragment : BaseBindingFragment<FragmentHomeBinding>() {

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var mainAdapter: MainArticleAdapter
    private lateinit var bannerAdapter: BannerAdapter

    override fun layoutRes() = R.layout.fragment_home

    override fun initView(view: View, isFirst: Boolean) {
        super.initView(view, isFirst)
        binding.refreshLayout.setColorSchemeResources(R.color.accent)
        mainAdapter = MainArticleAdapter(this)
        bannerAdapter = BannerAdapter(this)
        binding.recyclerView.adapter = ConcatAdapter(
            bannerAdapter,
            mainAdapter.withLoadStateFooter(CommonFooterAdapter { mainAdapter.retry() })
        )
        binding.refreshLayout.setOnRefreshListener {
            viewModel.getArticlesAndBanners()
            mainAdapter.refresh()
        }
        mainAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.Loading -> binding.refreshLayout.isRefreshing = true
                else -> binding.refreshLayout.isRefreshing = false
            }
        }
        lifecycleScope.launchWhenCreated { viewModel.getArticlesAndBanners() }
    }

    override fun observe() {
        super.observe()
        viewModel.also { model ->
            model.banners.observe(this) { bannerAdapter.setData(it) }
            lifecycleScope.launch {
                model.pager.collectLatest { mainAdapter.submitData(it) }
            }
        }
    }
}