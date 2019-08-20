package com.example.wanandroid.mvp.web;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebConfig;
import com.just.agentweb.DefaultWebClient;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author gaofengpeng
 * @date 2019/8/15
 * @description : 浏览文章
 */
public class WebActivity extends AppCompatActivity {


    @BindView(R.id.web_title)
    TextView mWebTitle;
    @BindView(R.id.web_close)
    FrameLayout mWebClose;
    @BindView(R.id.parent)
    FrameLayout mParent;
    private AgentWeb mAgentWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);

        String url = getIntent().getStringExtra("link");
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "https://" + url;
        if (url.startsWith("http://")) {
            url = url.replace("http://", "https://");
        }

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mParent, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
                .useDefaultIndicator()
                .defaultProgressBarColor()
                .setReceivedTitleCallback((view, title) -> mWebTitle.setText(title))
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW)
                .createAgentWeb()
                .ready()
                .go(url);
        


        mWebClose.setOnClickListener(v -> {
//            new AlertDialog.Builder(WebActivity.this)
//                    .setTitle("关闭网页")
//                    .setMessage("是否要关闭网页？")
//                    .setPositiveButton("确定", (dialog, which) -> finish())
//                    .setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
//                    .create().show();
            finish();

        });
    }

    @Override
    public void onBackPressed() {
        if (!mAgentWeb.back()) {
            new AlertDialog.Builder(WebActivity.this)
                    .setTitle("退出")
                    .setMessage("是否要退出浏览？")
                    .setPositiveButton("确定", (dialog, which) -> finish())
                    .setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
                    .create().show();
        }
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }
}
