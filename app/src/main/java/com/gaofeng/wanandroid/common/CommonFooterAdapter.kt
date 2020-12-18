package com.gaofeng.wanandroid.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.gaofeng.wanandroid.BR
import com.gaofeng.wanandroid.databinding.ItemLoadStateFooterBinding

/**
 *
 * @author 高峰
 * @date 2020/12/7 15:51
 * @desc 通用Footer
 */
class CommonFooterAdapter(private val onClick: () -> Unit) :
    LoadStateAdapter<CommonBindingViewHolder>() {
    override fun onBindViewHolder(
        holder: CommonBindingViewHolder,
        loadState: LoadState
    ) {
        holder.binding.setVariable(BR.state,loadState)
        holder.binding.setVariable(BR.click,object :View.OnClickListener{
            override fun onClick(v: View?) {
                onClick()
            }
        })
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): CommonBindingViewHolder {
        val binding =
            ItemLoadStateFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommonBindingViewHolder(binding)
    }

    override fun displayLoadStateAsItem(loadState: LoadState) = true
}