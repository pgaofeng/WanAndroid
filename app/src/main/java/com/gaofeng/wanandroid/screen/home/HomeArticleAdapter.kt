package com.gaofeng.wanandroid.screen.home

import androidx.core.text.toSpanned
import androidx.lifecycle.LifecycleOwner
import com.gaofeng.wanandroid.R
import com.gaofeng.wanandroid.base.BaseAdapter
import com.gaofeng.wanandroid.bean.ArticleBean
import com.gaofeng.wanandroid.databinding.ItemArticleBinding

/**
 *
 * @author 高峰
 * @date 2020/12/4 13:47
 * @desc 首页文章adapter
 */
class HomeArticleAdapter(private val lifecycleOwner: LifecycleOwner) :
    BaseAdapter<ArticleBean, ItemArticleBinding>(R.layout.item_article) {
    override fun binding(binding: ItemArticleBinding, item: ArticleBean?) {
        binding.lifecycleOwner = lifecycleOwner
        binding.article = item
        binding.tvTitle.text = item?.title?.toSpanned()
        binding.tvContent.text = item?.title?.toSpanned()
    }
}