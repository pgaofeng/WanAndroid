package com.gaofeng.wanandroid.screen.home.adapter

import android.text.Html
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import com.gaofeng.wanandroid.R
import com.gaofeng.wanandroid.base.BaseAdapterWithPaging
import com.gaofeng.wanandroid.bean.Article
import com.gaofeng.wanandroid.databinding.ItemArticleBinding

/**
 *
 * @author 高峰
 * @date 2020/12/7 10:46
 * @desc 主界面
 */
class MainArticleAdapter(lifecycleOwner: LifecycleOwner) :
    BaseAdapterWithPaging<Article, ItemArticleBinding>(
        R.layout.item_article,
        lifecycleOwner,
        diffUtil
    ) {

    override fun binding(binding: ItemArticleBinding, item: Article?) {
        binding.article = item
        binding.tvTitle.text = Html.fromHtml(item?.title, Html.FROM_HTML_MODE_LEGACY)
        binding.tvContent.text = Html.fromHtml(item?.desc, Html.FROM_HTML_MODE_LEGACY)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article) =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.id == newItem.id
                        && oldItem.title == newItem.title
            }
        }
    }
}
