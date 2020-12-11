package com.gaofeng.wanandroid.screen.home.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.gaofeng.wanandroid.R
import com.gaofeng.wanandroid.base.BaseBindingFragment
import com.gaofeng.wanandroid.common.CommonFooterAdapter
import com.gaofeng.wanandroid.databinding.FragmentArticleBinding
import com.gaofeng.wanandroid.ext.visible
import com.gaofeng.wanandroid.screen.home.MainArticleAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 *
 * @author 高峰
 * @date 2020/12/11 14:06
 * @desc 文章展示Fragment
 */
@AndroidEntryPoint
class ArticleFragment private constructor() : BaseBindingFragment<FragmentArticleBinding>() {
    override fun layoutRes() = R.layout.fragment_article

    private val viewModel by viewModels<ArticleViewModel>()
    private lateinit var mainAdapter: MainArticleAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val type = savedInstanceState?.getInt(ARTICLE_TYPE)
        viewModel.setType(type)
        super.onActivityCreated(savedInstanceState)
    }

    override fun initView(view: View, isFirst: Boolean) {
        super.initView(view, isFirst)
        mainAdapter = MainArticleAdapter(this)
        binding.apply {
            mainAdapter.addLoadStateListener {
                when (it.refresh) {
                    is LoadState.Loading -> refreshLayout.isRefreshing = true
                    else -> refreshLayout.isRefreshing = false
                }
            }
            recyclerView.adapter =
                mainAdapter.withLoadStateFooter(CommonFooterAdapter { mainAdapter.retry() })
            refreshLayout.apply {
                setColorSchemeResources(R.color.accent)
                setOnRefreshListener { mainAdapter.refresh() }
            }
        }
    }

    override fun observe() {
        super.observe()
        viewModel.also { model ->
            lifecycleScope.launch {
                model.pager.collectLatest { mainAdapter.submitData(it) }
            }
            model.empty.observe(this) {
                binding.emptyView.visible = it
                binding.recyclerView.visible = !it
            }
        }
    }


    companion object {
        const val ARTICLE_TYPE = "article type"

        /**
         * Create a fragment with the article type
         */
        fun create(type: Int) = ArticleFragment().apply {
            println("create fragment with : $type")
            val bundle = Bundle()
            bundle.putInt(ARTICLE_TYPE, type)
            arguments = bundle
        }
    }
}