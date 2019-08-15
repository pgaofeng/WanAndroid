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

    public interface View extends com.pgaofeng.common.mvp.View {
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

        /**
         * 成功添加一个TODO
         *
         * @param bean TODO对象
         */
        void addTodoSuccess(TodoBean bean);

        /**
         * 删除TODO成功
         *
         * @param position 删除位置
         */
        void delTodoSuccess(int position);


        /**
         * 修改TODO成功
         *
         * @param position 修改位置
         * @param bean     修改后的对象
         */
        void updateTodoSuccess(int position, TodoBean bean);

        /**
         * 网络请求失败
         *
         * @param message 失败原因
         */
        void onFail(String message);

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

        /**
         * 添加一个TODO
         *
         * @param title   标题
         * @param content 内容
         * @param date    日期
         */
        void addTodo(String title, String content, String date, ModelCallback callback);

        /**
         * 修改一个TODO
         *
         * @param id       id
         * @param title    标题
         * @param content  内容
         * @param date     日期
         * @param status   状态 0待完成，1已完成
         * @param callback 回调接口
         */
        void updateTodo(int id, String title, String content, String date, int status, ModelCallback callback);

        /**
         * 删除一个TODO
         *
         * @param id       　id
         * @param callback 回调接口
         */
        void delTodo(int id, ModelCallback callback);

        /**
         * 更新TODO的状态
         *
         * @param id       id
         * @param status   状态 0待完成，1已完成
         * @param callback 回调接口
         */
        void updateTodoState(int id, int status, ModelCallback callback);
    }

    public interface Presenter {
        /**
         * 获取Todo列表
         *
         * @param page   页数
         * @param status 状态，0未完成1已完成
         */
        void getTodoList(int page, int status);

        /**
         * 添加一个TODO
         *
         * @param title   标题
         * @param content 内容
         * @param date    日期
         */
        void addTodo(String title, String content, String date);

        /**
         * 修改一个TODO
         *
         * @param id       id
         * @param title    标题
         * @param content  内容
         * @param date     日期
         * @param position 修改位置
         * @param status   状态 0待完成，1已完成
         */
        void updateTodo(int id, String title, String content, String date, int position, int status);

        /**
         * 删除一个TODO
         *
         * @param id       　id
         * @param position 修改位置
         */
        void delTodo(int id, int position);

        /**
         * 更新TODO的状态
         *
         * @param id       id
         * @param status   状态 0待完成，1已完成
         * @param position 修改位置
         */
        void updateTodoState(int id, int status, int position);
    }
}
