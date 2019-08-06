package com.example.wanandroid.mvp.wechat.presenter;

import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.mvp.wechat.contract.WeChatArticleContract;
import com.example.wanandroid.mvp.wechat.model.WeChatModel;
import com.example.wanandroid.mvp.wechat.view.WeCharArticleFragment;
import com.example.wanandroid.network.ModelCallback;
import com.pgaofeng.common.base.BasePresenter;

import java.util.List;

/**
 * @author gaofengpeng
 * @date 2019/8/2
 * @description :
 */
public class WeChatArticlePresenter extends BasePresenter<WeCharArticleFragment, WeChatModel> implements WeChatArticleContract.Presenter {
    public WeChatArticlePresenter(WeCharArticleFragment view) {
        super(view);
    }

    @Override
    public void getArticleList(int id, int page) {
        mModel.getArticleList(id, page, new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.getArticleListSuccess((ArticleBean) baseData.getData());
            }

            @Override
            public void fail(Throwable throwable) {
                mView.getArticleListFail(throwable.getMessage());
            }
        });
    }

    @Override
    protected WeChatModel createModel() {
        return new WeChatModel();
    }
}
