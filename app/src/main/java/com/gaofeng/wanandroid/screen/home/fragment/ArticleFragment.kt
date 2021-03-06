package com.gaofeng.wanandroid.screen.home.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DiffUtil
import com.gaofeng.wanandroid.BR
import com.gaofeng.wanandroid.R
import com.gaofeng.wanandroid.base.BaseBindingFragment
import com.gaofeng.wanandroid.bean.Article
import com.gaofeng.wanandroid.common.CommonFooterAdapter
import com.gaofeng.wanandroid.common.CommonPagingAdapter
import com.gaofeng.wanandroid.databinding.FragmentArticleBinding
import com.gaofeng.wanandroid.ext.visible
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
    private lateinit var mainAdapter: CommonPagingAdapter<Article>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val type = savedInstanceState?.getInt(ARTICLE_TYPE)
        viewModel.setType(type)
        super.onActivityCreated(savedInstanceState)
    }

    override fun initView(view: View, isFirst: Boolean) {
        super.initView(view, isFirst)
        //    mainAdapter = MainArticleAdapter(this)

        mainAdapter = CommonPagingAdapter(
            BR.article,
            this,
            mapOf(0 to R.layout.item_article),
            object : DiffUtil.ItemCallback<Article>() {
                override fun areItemsTheSame(oldItem: Article, newItem: Article) =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(oldItem: Article, newItem: Article) =
                    oldItem == newItem
            })

        binding.apply {
            mainAdapter.addLoadStateListener {
                when (it.refresh) {
                    is LoadState.Loading -> refreshLayout.isRefreshing = true
                    else -> refreshLayout.isRefreshing = false
                }
                when {
                    it.refresh is LoadState.Error -> dealError(it.refresh as LoadState.Error)
                    it.append is LoadState.Error -> dealError(it.append as LoadState.Error)
                    it.prepend is LoadState.Error -> dealError(it.prepend as LoadState.Error)
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

    private fun dealError(error: LoadState.Error) {
        // 可以在这里处理Paging异常
        error.error.printStackTrace()
        Toast.makeText(requireContext(),error.error.message?:"unknown",Toast.LENGTH_SHORT).show()
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
            val bundle = Bundle()
            bundle.putInt(ARTICLE_TYPE, type)
            arguments = bundle
        }
    }
}