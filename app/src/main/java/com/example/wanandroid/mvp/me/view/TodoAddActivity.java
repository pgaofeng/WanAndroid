package com.example.wanandroid.mvp.me.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.BasePageBean;
import com.example.wanandroid.bean.TodoBean;
import com.example.wanandroid.mvp.me.contract.TodoContract;
import com.example.wanandroid.mvp.me.presenter.TodoPresenter;
import com.example.wanandroid.util.EventBusUtils;
import com.google.gson.Gson;
import com.pgaofeng.common.base.BaseActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.os.Build.VERSION_CODES.N;

/**
 * @author gaofengpeng
 * @date 2019/8/14
 * @description :
 */
public class TodoAddActivity extends BaseActivity<TodoPresenter> implements TodoContract.View {
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
    private TodoBean bean = null;
    private int position;
    private int status;

    @Override
    protected int getContentView() {
        return R.layout.activity_todo_add;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected TodoPresenter createPresenter() {
        return new TodoPresenter(this);
    }


    @RequiresApi(api = N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    @RequiresApi(api = N)
    private void init() {

        mTodoAddBack.setOnClickListener(v -> finish());
        mTodoAddOk.setOnClickListener(v -> commit());

        position = getIntent().getIntExtra("POSITION", -1);
        status = getIntent().getIntExtra("status", -1);
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
            if (status == 1) {
                mTodoAddRadioGroup.setVisibility(View.GONE);
                mTodoAddTime.setEnabled(false);
            }
            mTodoAddRadioGroup.check(bean.getStatus() == 0 ? R.id.todo_add_radio_pending : R.id.todo_add_radio_finish);
        }
        // 选择时间
        mTodoAddTime.setOnClickListener(v -> {
            DatePickerDialog dialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT);
            dialog.setOnDateSetListener((view, year, month, dayOfMonth) -> {
                Calendar time = Calendar.getInstance();
                time.set(year, month, dayOfMonth);
                Calendar now = Calendar.getInstance();
                // 只取当前日期，去除时间
                DateFormat dateFormat = SimpleDateFormat.getDateInstance();
                try {
                    now.setTime(dateFormat.parse(dateFormat.format(new Date())));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (time.before(now)) {
                    Toast.makeText(mContext, "只能选择当前日期往后", Toast.LENGTH_SHORT).show();
                    return;
                }
                String s = year + "-" + (month + 1) + "-" + dayOfMonth;
                mTodoAddTime.setText(s);
            });
            dialog.show();
        });
    }

    /**
     * 点击右上角OK键
     */
    private void commit() {
        String title = mTodoAddTitle.getText().toString().trim();
        String content = mTodoAddContent.getText().toString().trim();
        String date = mTodoAddTime.getText().toString().trim();
        int status = mTodoAddRadioPending.isChecked() ? 0 : 1;
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content)) {
            Toast.makeText(mContext, "标题或详情不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (bean == null) {
            mPresenter.addTodo(title, content, date);
        } else {
            mPresenter.updateTodo(bean.getId(), title, content, date, position, status);
        }
    }

    @Override
    public void getTodoListSuccess(BasePageBean<List<TodoBean>> data) {

    }

    @Override
    public void getTodoListFail(String message) {

    }

    @Override
    public void addTodoSuccess(TodoBean bean) {
        bean.setType(-1);
        EventBusUtils.sendObject(bean);
        finish();
    }

    @Override
    public void delTodoSuccess(int position) {

    }

    @Override
    public void updateTodoSuccess(int position, TodoBean bean) {
        bean.setType(position);
        bean.setPriority(status);
        EventBusUtils.sendObject(bean);
        finish();
    }

    @Override
    public void onFail(String message) {

    }

    @Override
    public void getTodoCacheSuccess(List<TodoBean> data) {

    }
}
