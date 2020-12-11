package com.gaofeng.wanandroid.screen.home.fragment

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.gaofeng.wanandroid.base.BaseViewModel
import com.gaofeng.wanandroid.bean.DataPaging
import com.gaofeng.wanandroid.common.CommonDataSource
import com.gaofeng.wanandroid.screen.home.HomeRepository
import com.gaofeng.wanandroid.screen.home.fragment.ArticleFragment.Companion.ARTICLE_TYPE
import kotlinx.coroutines.flow.catch

/**
 *
 * @author 高峰
 * @date 2020/12/4 10:09
 * @desc HomeViewModel
 */
class ArticleViewModel @ViewModelInject constructor(
    private val repository: HomeRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private var type: Int = savedStateHandle[ARTICLE_TYPE] ?: 0

    fun setType(type: Int?) {
        type ?: return
        this.type = type
        savedStateHandle[ARTICLE_TYPE] = type
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

                println("开始获取数据：type=$type,page=$page")

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
        }.flow.cachedIn(viewModelScope).catch { it.printStackTrace() }
    }
}