package com.gaofeng.wanandroid.screen.home.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.gaofeng.wanandroid.base.BaseViewModel
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

    val banners: MutableLiveData<List<List<String>>> by lazy { MutableLiveData() }
    val pager by lazy {
        Pager(PagingConfig(pageSize = 20, prefetchDistance = 10)) {
            CommonDataSource { page ->
                if (page == 0)
                    repository.getHomeTopArticle() + repository.getHomeMainArticle(page)
                repository.getHomeMainArticle(page)
            }
        }
    }

    /**
     * 获取置顶文章和Banner
     */
    fun getArticlesAndBanners() {
        launch {
            val bannerList = repository.getBanner()
            banners.value = listOf(bannerList.map { it.imagePath })
        }
    }
}