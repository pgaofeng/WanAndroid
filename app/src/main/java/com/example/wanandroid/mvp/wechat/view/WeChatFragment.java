package com.example.wanandroid.mvp.wechat.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.WeChatBean;
import com.example.wanandroid.mvp.wechat.contract.WeChatContract;
import com.example.wanandroid.mvp.wechat.presenter.WeChatPresenter;
import com.pgaofeng.common.base.BaseFragment;

import java.util.List;

/**
 * @author gaofengpeng
 * @date 2019/7/28
 * ${DESCRIPTION}
 */
public class WeChatFragment extends BaseFragment<WeChatPresenter> implements WeChatContract.View {

    private ViewPager viewPager;
    private TabLayout tabLayout;


    @Override
    protected int getContentView() {
        return R.layout.fragment_wechat;
    }

    @Override
    protected void initView(View view) {
        viewPager = view.findViewById(R.id.weChat_pager);
        tabLayout = view.findViewById(R.id.weChat_tab);
       // tabLayout.setupWithViewPager(viewPager);

        mPresenter.getWxList();
    }

    @Override
    protected WeChatPresenter createPresenter() {
        return new WeChatPresenter(this);
    }

    @Override
    public void getWxListSuccess(BaseResponse<List<WeChatBean>> data) {
        for (WeChatBean bean : data.getData()) {
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText(bean.getName());
            tabLayout.addTab(tab);
        }
    }

    @Override
    public void getWxListFail(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
}