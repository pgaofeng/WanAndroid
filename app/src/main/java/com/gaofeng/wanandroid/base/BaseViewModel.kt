package com.gaofeng.wanandroid.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 *
 * @author 高峰
 * @date 2020/11/25 16:12
 * @desc ViewModel 基类
 */
open class BaseViewModel : ViewModel(){

    fun launch(block:suspend ()->Unit){
        viewModelScope.launch {
            block()
        }
    }


}