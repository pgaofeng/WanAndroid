package com.gaofeng.wanandroid.screen.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.gaofeng.wanandroid.base.BaseViewModel
import com.gaofeng.wanandroid.bean.ArticleBean

/**
 *
 * @author 高峰
 * @date 2020/12/4 10:09
 * @desc HomeViewModel
 */
class HomeViewModel @ViewModelInject constructor(
    private val repository: HomeRepository
) : BaseViewModel() {

    val articles: MutableLiveData<List<ArticleBean>> by lazy { MutableLiveData() }

    fun getTopArticle() {
        launch {
            val list = repository.getHomeTopArticle()
            articles.value = list
        }
    }


}