package com.example.wanandroid.mvp.me.view;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.pgaofeng.common.base.BaseFragment;
import com.pgaofeng.common.mvp.Presenter;

/**
 * @author MeFragment
 * @date 2019/7/28
 * 我的
 */
public class MeFragment extends BaseFragment {

    private TextView mTextView;

    @Override
    protected int getContentView() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView(View view) {
        mTextView = view.findViewById(R.id.me_item_text);
        mTextView.setOnClickListener(v -> {
            startActivity(new Intent(mContext, CollectActivity.class));
        });
    }

    @Override
    protected Presenter createPresenter() {
        return null;
    }
}
