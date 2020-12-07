package com.gaofeng.wanandroid.common

import androidx.paging.PagingSource
import com.gaofeng.wanandroid.bean.DataPaging

/**
 *
 * @author 高峰
 * @date 2020/12/7 13:33
 * @desc 通用数据源DataSource，提供给Paging使用，参数[block]的返回值必须是[DataPaging]格式
 */
class CommonDataSource<V : Any, DP : DataPaging<V>>(private val block: suspend (page: Int) -> DP) :
    PagingSource<Int, V>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, V> {
        val page = params.key ?: 0
        return try {
            val resultData = block(page)
            LoadResult.Page(
                data = resultData.datas,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (resultData.over) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}