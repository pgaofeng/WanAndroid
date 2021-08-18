package com.gaofeng.wanandroid.ui.home.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.gaofeng.wanandroid.base.BaseViewModel
import com.gaofeng.wanandroid.bean.DataPaging
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

    private var type: Int = 0

    fun setType(type: Int?) {
        type ?: return
        this.type = type
    }

    /**
     * 当前列表是否为空
     */
    val empty = MutableLiveData(true)

    /**
     * 文章数据
     */
    val pager by lazy {
        Pager(PagingConfig(pageSize = 20, prefetchDistance = 10)) {
            CommonDataSource { page ->
                when (type) {
                    0 -> {
                        if (page == 0) {
                            val main = async { repository.getHomeMainArticle(page) }
                            val top = async { repository.getHomeTopArticle() }
                            DataPaging(datas = top.await()
                                .map { it.apply { isTop = true } } + main.await().datas,
                                over = main.await().over
                            )
                        } else {
                            async { repository.getHomeMainArticle(page) }.await()
                        }
                    }
                    1 -> repository.getSquareArticle(page)
                    2 -> repository.getAnswerArticle(page)
                    else -> repository.getAnswerArticle(page)
                }.also { paging ->
                    empty.value = paging.datas.isEmpty()
                }
            }
        }.flow.cachedIn(viewModelScope)
    }
}