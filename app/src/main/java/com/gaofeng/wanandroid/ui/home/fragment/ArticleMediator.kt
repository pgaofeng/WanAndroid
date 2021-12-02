package com.gaofeng.wanandroid.ui.home.fragment

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.gaofeng.wanandroid.bean.Article
import com.gaofeng.wanandroid.db.ArticleDao
import com.gaofeng.wanandroid.ui.home.HomeRepository
import javax.inject.Inject

/**
 * @author gaofeng
 * @date 2021/12/2
 */
@OptIn(ExperimentalPagingApi::class)
class ArticleMediator @Inject constructor(
    private val remote: HomeRepository,
    private val local: ArticleDao
) : RemoteMediator<Int, Article>() {

    private var mPage = 0

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult {
        try {
            when (loadType) {
                LoadType.REFRESH -> mPage = 0
                LoadType.PREPEND -> return MediatorResult.Success(true)
                LoadType.APPEND -> mPage++
            }
            val removeData = remote.getHomeMainArticle(mPage)
            if (loadType == LoadType.REFRESH) {
                local.delete()
            }
            local.insertAll(removeData.datas)
            return MediatorResult.Success(removeData.datas.isEmpty())
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }
}