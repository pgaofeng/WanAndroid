package com.gaofeng.wanandroid.screen.home.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.gaofeng.wanandroid.base.BaseViewModel
import com.gaofeng.wanandroid.bean.DataPaging
import com.gaofeng.wanandroid.common.CommonDataSource
import com.gaofeng.wanandroid.screen.home.repository.HomeRepository

/**
 *
 * @author 高峰
 * @date 2020/12/4 10:09
 * @desc HomeViewModel
 */
class HomeViewModel @ViewModelInject constructor(
    private val repository: HomeRepository
) : BaseViewModel() {

    /**
     * banner数据
     */
    val banners: MutableLiveData<List<List<String>>> by lazy { MutableLiveData() }

    /**
     * 文章数据
     */
    val pager by lazy {
        Pager(PagingConfig(pageSize = 20, prefetchDistance = 10)) {
            CommonDataSource { page ->
                val main = async { repository.getHomeMainArticle(page) }
                if (page == 0) {
                    val top = async { repository.getHomeTopArticle() }
                    val banner = async { repository.getBanner() }
                    DataPaging(
                        datas = top.await().map { it.apply { isTop = true } } + main.await().datas,
                        over = main.await().over
                    ).also { paging ->
                        banners.value = listOf(banner.await().map { it.imagePath })
                        empty.value = paging.datas.isEmpty()
                    }
                } else {
                    main.await()
                }
            }
        }.flow.cachedIn(viewModelScope)
    }

    /**
     * 当前列表是否为空
     */
    val empty = MutableLiveData(true)
}