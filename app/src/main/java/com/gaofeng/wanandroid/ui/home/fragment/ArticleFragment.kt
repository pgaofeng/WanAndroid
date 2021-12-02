package com.gaofeng.wanandroid.ui.home.fragment

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import com.gaofeng.wanandroid.R
import com.gaofeng.wanandroid.base.BaseBindingFragment
import com.gaofeng.wanandroid.common.CommonFooterAdapter
import com.gaofeng.wanandroid.databinding.FragmentArticleBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 * @author 高峰
 * @date 2020/12/11 14:06
 * @desc 文章展示Fragment
 */
@AndroidEntryPoint
class ArticleFragment private constructor() : BaseBindingFragment<FragmentArticleBinding>() {

    override val layoutId = R.layout.fragment_article

    private val viewModel: ArticleViewModel by viewModels()

    @Inject
    lateinit var mMainAdapter: ArticleAdapter

    override fun init() {
        super.init()
        viewModel.type = arguments?.getInt(ARTICLE_TYPE) ?: 0
        mMainAdapter.addLoadStateListener {
            binding.refreshLayout.isRefreshing = it.refresh == LoadState.Loading
            when {
                it.refresh is LoadState.Error -> dealError(it.refresh as LoadState.Error)
                it.append is LoadState.Error -> dealError(it.append as LoadState.Error)
                it.prepend is LoadState.Error -> dealError(it.prepend as LoadState.Error)
            }
        }
        binding.recyclerView.adapter =
            mMainAdapter.withLoadStateFooter(CommonFooterAdapter { mMainAdapter.retry() })
        binding.refreshLayout.setOnRefreshListener { mMainAdapter.refresh() }

        lifecycleScope.launch {
            viewModel.pagerFlow.collectLatest {
                mMainAdapter.submitData(it)
            }
        }
    }

    private fun dealError(error: LoadState.Error) {
        // 可以在这里处理Paging异常
        error.error.printStackTrace()
        Toast.makeText(requireContext(), error.error.message ?: "unknown", Toast.LENGTH_SHORT)
            .show()
    }


    companion object {
        const val ARTICLE_TYPE = "article type"

        fun create(type: Int) = ArticleFragment().apply {
            val bundle = Bundle()
            bundle.putInt(ARTICLE_TYPE, type)
            arguments = bundle
        }
    }
}