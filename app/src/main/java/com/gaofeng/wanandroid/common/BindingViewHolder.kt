package com.gaofeng.wanandroid.common

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 *
 * @author 高峰
 * @date 2020/12/7 15:54
 * @desc 通用ViewHolder
 */
class BindingViewHolder<VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)