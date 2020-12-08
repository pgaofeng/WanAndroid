package com.gaofeng.wanandroid.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.gaofeng.wanandroid.databinding.ItemLoadStateFooterBinding

/**
 *
 * @author 高峰
 * @date 2020/12/7 15:51
 * @desc 通用Footer
 */
class CommonFooterAdapter(private val onClick: () -> Unit) :
    LoadStateAdapter<BindingViewHolder<ItemLoadStateFooterBinding>>() {
    override fun onBindViewHolder(
        holder: BindingViewHolder<ItemLoadStateFooterBinding>,
        loadState: LoadState
    ) {
        holder.binding.tvRetry.setOnClickListener { onClick() }
        holder.binding.state = loadState
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): BindingViewHolder<ItemLoadStateFooterBinding> {
        val binding =
            ItemLoadStateFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindingViewHolder(binding)
    }

    override fun displayLoadStateAsItem(loadState: LoadState) = true
}