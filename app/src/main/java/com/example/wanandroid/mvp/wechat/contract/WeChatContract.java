package com.example.wanandroid.mvp.wechat.contract;

import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.WeChatBean;
import com.example.wanandroid.network.ModelCallback;

import java.util.List;

/**
 * @author WeChatContract
 * @date 2019/8/1
 * ${DESCRIPTION}
 */
public class WeChatContract {
    public interface View {
        /**
         * 获取微信列表成功
         *
         * @param data 列表数据
         */
        void getWxListSuccess(BaseResponse<List<WeChatBean>> data);

        /**
         * 获取微信列表失败
         *
         * @param message 失败原因
         */
        void getWxListFail(String message);
    }

    public interface Model {

        /**
         * 获取微信列表
         *
         * @param callback 回调接口
         */
        void getWxList(ModelCallback callback);
    }

    public interface Presenter {
        /**
         * 获取微信列表
         */
        void getWxList();
    }
}
