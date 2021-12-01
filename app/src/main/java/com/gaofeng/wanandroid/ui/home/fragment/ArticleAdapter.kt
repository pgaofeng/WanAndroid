package com.gaofeng.wanandroid.ui.home.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gaofeng.wanandroid.R
import com.gaofeng.wanandroid.bean.Article
import com.gaofeng.wanandroid.databinding.ItemArticleBinding
import javax.inject.Inject

/**
 * @author gaofeng
 * @date 2021/12/1
 */
class ArticleAdapter @Inject constructor() :
    PagingDataAdapter<Article, ArticleAdapter.ArticleViewHolder>(DIFF) {

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding: ItemArticleBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_article, parent, false
        )
        return ArticleViewHolder(binding)
    }

    class ArticleViewHolder(
        private val binding: ItemArticleBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article?) {
            binding.article = article
        }
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Article, newItem: Article) =
                oldItem == newItem
        }
    }
}