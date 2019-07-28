package com.example.wanandroid.mvp.main.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.mvp.home.HomeFragment;
import com.example.wanandroid.mvp.main.contract.MainActivityContract;
import com.example.wanandroid.mvp.me.MeFragment;
import com.example.wanandroid.mvp.type.TypeFragment;
import com.example.wanandroid.mvp.wechat.WeChatFragment;
import com.pgaofeng.common.base.BaseActivity;
import com.pgaofeng.common.mvp.Presenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainActivityContract.View {

    @BindView(R.id.main_frame)
    FrameLayout mainFrame;
    @BindView(R.id.main_home_img)
    ImageView mainHomeImg;
    @BindView(R.id.main_home_text)
    TextView mainHomeText;
    @BindView(R.id.main_home)
    LinearLayout mainHome;
    @BindView(R.id.main_weChat_img)
    ImageView mainWeChatImg;
    @BindView(R.id.main_weChat_text)
    TextView mainWeChatText;
    @BindView(R.id.main_weChat)
    LinearLayout mainWeChat;
    @BindView(R.id.main_type_img)
    ImageView mainTypeImg;
    @BindView(R.id.main_type_text)
    TextView mainTypeText;
    @BindView(R.id.main_type)
    LinearLayout mainType;
    @BindView(R.id.main_me_img)
    ImageView mainMeImg;
    @BindView(R.id.main_me_text)
    TextView mainMeText;
    @BindView(R.id.main_me)
    LinearLayout mainMe;

    /**
     * 首页
     */
    private Fragment mHome = null;
    /**
     * 公众号
     */
    private Fragment mWeChat = null;
    /**
     * 分类
     */
    private Fragment mType = null;
    /**
     * 我的
     */
    private Fragment mMe = null;

    /**
     * 用于记录上一个选择的tab
     */
    private Fragment mPreFragment = null;


    private FragmentManager manager;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        manager = getSupportFragmentManager();
        mHome = new HomeFragment();
        manager.beginTransaction().add(R.id.main_frame, mHome, HomeFragment.class.getName()).show(mHome).commitAllowingStateLoss();
        mPreFragment = mHome;
    }

    @Override
    protected Presenter createPresenter() {
        return null;
    }

    @OnClick({R.id.main_home, R.id.main_type, R.id.main_me, R.id.main_weChat})
    public void onClick(View view) {
        int tabIndex = -1;
        switch (view.getId()) {
            case R.id.main_home:
                tabIndex = 0;
                break;
            case R.id.main_weChat:
                tabIndex = 1;
                break;
            case R.id.main_type:
                tabIndex = 2;
                break;
            case R.id.main_me:
                tabIndex = 3;
                break;
            default:
                tabIndex = 0;
                break;
        }
        switchTab(tabIndex);
    }

    @Override
    public void switchTab(int index) {
        mainHomeImg.setImageResource(R.mipmap.ic_home);
        mainHomeText.setTextColor(getResources().getColor(R.color.black));
        mainTypeImg.setImageResource(R.mipmap.ic_type);
        mainTypeText.setTextColor(getResources().getColor(R.color.black));
        mainWeChatImg.setImageResource(R.mipmap.ic_wechat);
        mainWeChatText.setTextColor(getResources().getColor(R.color.black));
        mainMeImg.setImageResource(R.mipmap.ic_me);
        mainMeText.setTextColor(getResources().getColor(R.color.black));

        switch (index) {
            case 0:
                mainHomeImg.setImageResource(R.mipmap.ic_home_selected);
                mainHomeText.setTextColor(getResources().getColor(R.color.common_page_selected));
                break;
            case 1:
                mainWeChatImg.setImageResource(R.mipmap.ic_wechat_selected);
                mainWeChatText.setTextColor(getResources().getColor(R.color.common_page_selected));
                break;
            case 2:
                mainTypeImg.setImageResource(R.mipmap.ic_type_selected);
                mainTypeText.setTextColor(getResources().getColor(R.color.common_page_selected));
                break;
            case 3:
                mainMeImg.setImageResource(R.mipmap.ic_me_selected);
                mainMeText.setTextColor(getResources().getColor(R.color.common_page_selected));
                break;
            default:
                break;
        }
        switchFragment(index);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    /**
     * 显示对应的Fragment
     *
     * @param index 从0-3对应三个模块
     */
    private void switchFragment(int index) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (index) {
            case 0:
                mHome = manager.findFragmentByTag(HomeFragment.class.getName());
                if (mHome == null) {
                    mHome = new HomeFragment();
                    transaction.add(R.id.main_frame, mHome, HomeFragment.class.getName());
                }
                if (mPreFragment == mHome) {
                    clickRepeat(mHome);
                } else {
                    transaction.hide(mPreFragment).show(mHome);
                }
                mPreFragment = mHome;
                break;
            case 1:
                mWeChat = manager.findFragmentByTag(WeChatFragment.class.getName());
                if (mWeChat == null) {
                    mWeChat = new WeChatFragment();
                    transaction.add(R.id.main_frame, mWeChat, WeChatFragment.class.getName());
                }
                if (mPreFragment == mWeChat) {
                    clickRepeat(mWeChat);
                } else {
                    transaction.hide(mPreFragment).show(mWeChat);
                }
                mPreFragment = mWeChat;
                break;
            case 2:
                mType = manager.findFragmentByTag(TypeFragment.class.getName());
                if (mType == null) {
                    mType = new TypeFragment();
                    transaction.add(R.id.main_frame, mType, TypeFragment.class.getName());
                }
                if (mPreFragment == mType) {
                    clickRepeat(mType);
                } else {
                    transaction.hide(mPreFragment).show(mType);
                }
                mPreFragment = mType;
                break;
            case 3:
                mMe = manager.findFragmentByTag(MeFragment.class.getName());
                if (mMe == null) {
                    mMe = new HomeFragment();
                    transaction.add(R.id.main_frame, mMe, MeFragment.class.getName());
                }
                if (mPreFragment == mMe) {
                    clickRepeat(mMe);
                } else {
                    transaction.hide(mPreFragment).show(mMe);
                }
                mPreFragment = mMe;
                break;
            default:
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    /**
     * 重复点击tab
     *
     * @param fragment 点击对应的Fragment
     */
    private void clickRepeat(Fragment fragment) {
        //
    }
}
