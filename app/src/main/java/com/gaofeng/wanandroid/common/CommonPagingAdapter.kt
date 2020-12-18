package com.gaofeng.wanandroid.common

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.gaofeng.wanandroid.BR
import com.gaofeng.wanandroid.bean.AdapterType

/**
 * @author 高峰
 * @date 2020/12/18
 *
 * 通用Paging3的adapter，完全使用Binding进行数据绑定。
 * 参数[bindingKey]是给xml设置的当前item，用于绑定基础数据
 * 参数[layoutResMap]是布局文件的map集合，key为ItemType，value为布局文件id。
 * <br>
 * 注意：若是单ItemType则[layoutResMap]只需要设置一个即可，key可为任意。若是多个ItemType，注意
 * 对应的ItemType和LayoutId，并且Model必须实现[AdapterType]接口。
 * <br>
 * 参数[extra]是额外参数，用于给xml设置其他的数据，比如点击事件等，之一key是[BR]对应的值。
 *
 */
class CommonPagingAdapter<T : Any>(
    private val bindingKey: Int,
    private val lifecycleOwner: LifecycleOwner,
    private val layoutResMap: Map<Int, Int>,
    private vararg val extra: Pair<Int, Any> = emptyArray()
) : PagingDataAdapter<T, CommonBindingViewHolder>(object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.hashCode() == newItem.hashCode()

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}) {

    override fun onBindViewHolder(holder: CommonBindingViewHolder, position: Int) {
        holder.binding.lifecycleOwner = lifecycleOwner
        holder.binding.setVariable(bindingKey, getItem(position))
        for ((key, value) in extra) {
            holder.binding.setVariable(key, value)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonBindingViewHolder {
        val layout = layoutResMap[viewType] ?: layoutResMap.entries.first().value

        val binding: ViewDataBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), layout, parent, false)
        return CommonBindingViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        if (item is AdapterType) {
            return item.itemType(position)
        }
        return DEFAULT
    }

    companion object {
        private const val DEFAULT = 132 shl 16
    }
}