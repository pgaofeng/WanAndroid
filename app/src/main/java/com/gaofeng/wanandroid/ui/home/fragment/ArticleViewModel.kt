package com.gaofeng.wanandroid.ui.home.fragment

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.gaofeng.wanandroid.base.BaseViewModel
import com.gaofeng.wanandroid.common.CommonDataSource
import com.gaofeng.wanandroid.ui.home.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *
 * @author 高峰
 * @date 2020/12/4 10:09
 * @desc HomeViewModel
 */
@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val repository: HomeRepository
) : BaseViewModel() {

    var type: Int = 0

    val pagerFlow = Pager(
        PagingConfig(pageSize = 20, prefetchDistance = 10),
    ) {
        CommonDataSource { page ->
            val data = when (type) {
                0 -> repository.getHomeMainArticle(page)
                1 -> repository.getSquareArticle(page)
                2 -> repository.getAnswerArticle(page)
                else -> repository.getAnswerArticle(page)
            }
            data
        }
    }.flow.cachedIn(viewModelScope)
}