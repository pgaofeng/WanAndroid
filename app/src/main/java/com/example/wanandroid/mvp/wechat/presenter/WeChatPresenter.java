package com.example.wanandroid.mvp.wechat.presenter;

import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.WeChatBean;
import com.example.wanandroid.mvp.wechat.contract.WeChatContract;
import com.example.wanandroid.mvp.wechat.model.WeChatModel;
import com.example.wanandroid.mvp.wechat.view.WeChatFragment;
import com.example.wanandroid.network.ModelCallback;
import com.pgaofeng.common.base.BasePresenter;

import java.util.List;

/**
 * @author WeChatPresenter
 * @date 2019/8/1
 * ${DESCRIPTION}
 */
public class WeChatPresenter extends BasePresenter<WeChatFragment, WeChatModel> implements WeChatContract.Presenter {
    public WeChatPresenter(WeChatFragment view) {
        super(view);
    }

    @Override
    protected WeChatModel createModel() {
        return new WeChatModel();
    }

    @Override
    public void getWxList() {
        mModel.getWxList(new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.getWxListSuccess((BaseResponse<List<WeChatBean>>) baseData);
            }

            @Override
            public void fail(Throwable throwable) {
                mView.getWxListFail(throwable.getMessage());
            }
        });
    }

    @Override
    public void getWxListCache() {
        mModel.getWxListCache(new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.getWxListSuccess((BaseResponse<List<WeChatBean>>) baseData);
            }

            @Override
            public void fail(Throwable throwable) {
                mView.getWxListFail(throwable.getMessage());
            }
        });
    }
}
