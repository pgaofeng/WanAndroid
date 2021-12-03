package com.gaofeng.wanandroid.ui.home.fragment

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.gaofeng.wanandroid.base.BaseViewModel
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
    private val repository: ArticleRepository
) : BaseViewModel() {

    var type: Int = 0

    val pagerFlow = repository.getArticlePaging(type)
        .flow
        .cachedIn(viewModelScope)
}