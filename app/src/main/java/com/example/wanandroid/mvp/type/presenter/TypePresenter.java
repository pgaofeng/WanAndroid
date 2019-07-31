package com.example.wanandroid.mvp.type.presenter;

import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.TypeBean;
import com.example.wanandroid.mvp.type.contract.TypeContract;
import com.example.wanandroid.mvp.type.model.TypeModel;
import com.example.wanandroid.mvp.type.view.TypeFragment;
import com.example.wanandroid.network.ModelCallback;
import com.pgaofeng.common.base.BasePresenter;

import java.util.List;

/**
 * @author gaofengpeng
 * @date 2019/7/31
 * @description :
 */
public class TypePresenter extends BasePresenter<TypeFragment, TypeModel> implements TypeContract.Presenter {
    public TypePresenter(TypeFragment view) {
        super(view);
    }

    @Override
    public void getTypeList() {
        mModel.getTypeList(new ModelCallback() {
            @Override
            public void success(BaseResponse<?> baseData) {
                mView.getTypeListSuccess((List<TypeBean>) baseData.getData());
            }

            @Override
            public void fail(Throwable throwable) {
                mView.getTypeListFail(throwable.getMessage());
            }
        });
    }

    @Override
    protected TypeModel createModel() {
        return new TypeModel();
    }
}
