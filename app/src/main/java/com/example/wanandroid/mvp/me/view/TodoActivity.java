package com.example.wanandroid.mvp.me.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.wanandroid.R;
import com.example.wanandroid.mvp.me.presenter.TodoPresenter;
import com.pgaofeng.common.base.BaseActivity;

/**
 * @author TodoActivity
 * @date 2019/8/11
 * ${DESCRIPTION}
 */
public class TodoActivity extends BaseActivity {

    private ViewPager pager;
    private TabLayout tabLayout;


    @Override
    protected int getContentView() {
        return R.layout.fragment_wechat;
    }

    @Override
    protected void initView() {
        tabLayout = findViewById(R.id.weChat_tab);
        pager = findViewById(R.id.weChat_pager);

        tabLayout.addTab(tabLayout.newTab().setText("未完成"));
        tabLayout.addTab(tabLayout.newTab().setText("已完成"));

        Fragment unFinish = new TodoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("status", 0);
        unFinish.setArguments(bundle);

        Fragment finish = new TodoFragment();
        Bundle bundle1 = new Bundle();
        bundle.putInt("status", 1);
        finish.setArguments(bundle1);

        PagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return i == 0 ? unFinish : finish;
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

    }

    @Override
    protected TodoPresenter createPresenter() {
        return null;
    }
}
