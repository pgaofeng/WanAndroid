package com.gaofeng.wanandroid.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.gaofeng.wanandroid.common.BindingViewHolder

/**
 *
 * @author 高峰
 * @date 2020/12/3 15:58
 * @desc 通用的RecyclerViewAdapter，使用Binding
 */
abstract class BaseAdapter<T : Any, B : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<BindingViewHolder<B>>() {

    private val dataList by lazy { mutableListOf<T?>() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<B> {
        val binding: B =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutRes, parent, false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<B>, position: Int) {
        holder.binding.lifecycleOwner = lifecycleOwner
        binding(holder.binding, dataList[position])
    }

    /**
     * Adapter绑定的实际方法，子类只需要在该方法为binding设置参数即可
     */
    abstract fun binding(binding: B, item: T?)

    override fun getItemCount() = dataList.size

    /**
     * 为Adapter设置数据集，当[isClear]为false时，则会追加数据。
     */
    fun setData(data: Iterable<T?>, isClear: Boolean = true) = if (isClear) {
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    } else {
        val currentSize = dataList.size
        dataList.addAll(data)
        notifyItemRangeInserted(currentSize - 1, data.count())
    }
}

/**
 *
 * @author 高峰
 * @desc 通用的RecyclerViewAdapter，使用Binding+Paging3
 */
abstract class BaseAdapterWithPaging<T : Any, B : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int,
    private val lifecycleOwner: LifecycleOwner,
    callback: DiffUtil.ItemCallback<T>
) : PagingDataAdapter<T, BindingViewHolder<B>>(callback) {

    /**
     * Adapter绑定的实际方法，子类只需要在该方法为binding设置参数即可
     */
    abstract fun binding(binding: B, item: T?)

    override fun onBindViewHolder(holder: BindingViewHolder<B>, position: Int) {
        holder.binding.lifecycleOwner = lifecycleOwner
        binding(holder.binding, getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<B> {
        val binding: B =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutRes, parent, false)
        return BindingViewHolder(binding)
    }
}