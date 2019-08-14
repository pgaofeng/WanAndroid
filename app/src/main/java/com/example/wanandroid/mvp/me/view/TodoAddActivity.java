package com.example.wanandroid.mvp.me.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.TodoBean;
import com.google.gson.Gson;
import com.pgaofeng.common.base.BaseActivity;
import com.pgaofeng.common.mvp.Presenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author gaofengpeng
 * @date 2019/8/14
 * @description :
 */
public class TodoAddActivity extends BaseActivity {
    @BindView(R.id.todo_add_actionBarTitle)
    TextView mTodoAddActionBarTitle;
    @BindView(R.id.todo_add_back)
    FrameLayout mTodoAddBack;
    @BindView(R.id.todo_add_ok)
    FrameLayout mTodoAddOk;
    @BindView(R.id.todo_add_title)
    EditText mTodoAddTitle;
    @BindView(R.id.todo_add_content)
    EditText mTodoAddContent;
    @BindView(R.id.todo_add_time)
    TextView mTodoAddTime;
    @BindView(R.id.todo_add_radio_pending)
    RadioButton mTodoAddRadioPending;
    @BindView(R.id.todo_add_radio_finish)
    RadioButton mTodoAddRadioFinish;
    @BindView(R.id.todo_add_radioGroup)
    RadioGroup mTodoAddRadioGroup;

    /**
     * 传递进来的Todo对象
     */
    private TodoBean bean;

    @Override
    protected int getContentView() {
        return R.layout.activity_todo_add;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected Presenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

        mTodoAddBack.setOnClickListener(v -> finish());

        String todoStr = getIntent().getStringExtra("TODOBEAN");
        if (TextUtils.isEmpty(todoStr)) {
            mTodoAddRadioGroup.setVisibility(View.GONE);
            mTodoAddActionBarTitle.setText("添加");
            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(new Date());
            mTodoAddTime.setText(date);
        } else {
            bean = new Gson().fromJson(todoStr, TodoBean.class);
            mTodoAddActionBarTitle.setText("修改");
            mTodoAddTitle.setText(bean.getTitle());
            mTodoAddContent.setText(bean.getContent());
            mTodoAddTime.setText(bean.getDateStr());
            mTodoAddRadioGroup.check(bean.getStatus() == 0 ? R.id.todo_add_radio_pending : R.id.todo_add_radio_finish);
        }
    }
}
