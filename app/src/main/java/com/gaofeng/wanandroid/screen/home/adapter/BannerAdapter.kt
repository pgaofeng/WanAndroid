package com.gaofeng.wanandroid.screen.home.adapter

import androidx.lifecycle.LifecycleOwner
import com.gaofeng.wanandroid.R
import com.gaofeng.wanandroid.base.BaseAdapter
import com.gaofeng.wanandroid.databinding.ItemHomeBannerBinding

/**
 *
 * @author 高峰
 * @date 2020/12/7 16:37
 * @desc 首页Banner对应的Adapter
 */
class BannerAdapter(lifecycleOwner: LifecycleOwner) :
    BaseAdapter<List<String>, ItemHomeBannerBinding>(R.layout.item_home_banner, lifecycleOwner) {
    override fun binding(binding: ItemHomeBannerBinding, item: List<String>?) {
        item?.also {
            binding.banner.setUrl(it)
        }
    }
}