package com.example.wanandroid.mvp.type.contract;

import com.example.wanandroid.bean.TypeBean;
import com.example.wanandroid.network.ModelCallback;

import java.util.List;

/**
 * @author gaofengpeng
 * @date 2019/7/31
 * @description : 分类模块契约类
 */
public class TypeContract {

    public interface View {
        /**
         * 获取分类数据成功
         *
         * @param datas 分类数据
         */
        void getTypeListSuccess(List<TypeBean> datas);

        /**
         * 获取分类数据失败
         *
         * @param message 失败信息
         */
        void getTypeListFail(String message);
    }

    public interface Presenter {
        /**
         * 获取分类数据
         */
        void getTypeList();
    }

    public interface Model {
        /**
         * 获取分类数据
         *
         * @param callback 回调接口
         */
        void getTypeList(ModelCallback callback);
    }

}
