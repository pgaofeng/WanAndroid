package com.example.wanandroid.mvp.me.contract;

import com.example.wanandroid.bean.BasePageBean;
import com.example.wanandroid.bean.TodoBean;
import com.example.wanandroid.network.ModelCallback;

import java.util.List;

/**
 * @author TodoContract
 * @date 2019/8/11
 * ${DESCRIPTION}
 */
public interface TodoContract {

    public interface View {
        /**
         * 获取Todo列表成功
         *
         * @param data 列表数据
         */
        void getTodoListSuccess(BasePageBean<List<TodoBean>> data);

        /**
         * 获取Todo列表失败
         *
         * @param message 失败信息
         */
        void getTodoListFail(String message);
    }

    public interface Model {

        /**
         * 获取Todo列表
         *
         * @param page     页数
         * @param status   状态，0未完成1已完成
         * @param callback
         */
        void getTodoList(int page, int status, ModelCallback callback);

    }

    public interface Presenter {
        /**
         * 获取Todo列表
         *
         * @param page   页数
         * @param status 状态，0未完成1已完成
         */
        void getTodoList(int page, int status);

    }
}
