package com.example.wanandroid.mvp.me.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.TodoBean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author gaofengpeng
 * @date 2019/8/12
 * @description :
 */
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {


    private Context mContext;
    private List<TodoBean> data;
    private OnItemClickListener listener;

    public TodoAdapter(Context context) {
        this.mContext = context;
        this.data = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        if (listener != null) {
            this.listener = listener;
        }
    }

    public void replace(TodoBean bean, int position) {
        this.data.remove(position);
        this.data.add(position, bean);
        notifyItemChanged(position);
    }

    public void remove(int position){
        this.data.remove(position);
        notifyItemRemoved(position);
    }

    public void setData(List<TodoBean> data) {
        int oldSize = this.data.size();
        int newSize = data.size();
        this.data.clear();
        this.data.addAll(data);
        if (oldSize > newSize) {
            notifyItemRangeRemoved(newSize, oldSize - newSize);
            notifyItemRangeChanged(0, newSize);
        } else if (oldSize == newSize) {
            notifyItemRangeChanged(0, newSize);
        } else {
            notifyItemRangeChanged(0, oldSize);
            notifyItemRangeInserted(oldSize, newSize - oldSize);
        }
    }

    public void addData(List<TodoBean> data) {
        int oldSize = this.data.size();
        int newSize = data.size();
        this.data.addAll(data);
        notifyItemRangeInserted(oldSize, newSize);
    }

    public void addItem(TodoBean bean) {
        this.data.add(0, bean);
        notifyItemInserted(0);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_todo, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mItemTodoTargetTime.setText(data.get(i).getDateStr());
        viewHolder.mItemTodoTitle.setText(data.get(i).getTitle());
        if (TextUtils.isEmpty(data.get(i).getContent())) {
            viewHolder.mItemTodoContent.setVisibility(View.GONE);
        } else {
            viewHolder.mItemTodoContent.setVisibility(View.VISIBLE);
            viewHolder.mItemTodoContent.setText(data.get(i).getContent());
        }

        // 待完成TODO
        if (data.get(i).getCompleteDate() == 0) {
            Calendar now = Calendar.getInstance();
            Calendar target = Calendar.getInstance();

            // 只取当前日期，去除时间
            DateFormat dateFormat = SimpleDateFormat.getDateInstance();
            try {
                now.setTime(dateFormat.parse(dateFormat.format(new Date())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            target.setTime(new Date(data.get(i).getDate()));
            if (now.after(target)) {
                // 超时红色背景
                viewHolder.mItemTodoType.setBackgroundResource(R.drawable.bg_home_item_top);
                viewHolder.mItemTodoType.setTextColor(Color.RED);
                viewHolder.mItemTodoType.setText("已超时");
            } else if (now.before(target)) {
                // 充足时间绿色背景
                viewHolder.mItemTodoType.setBackgroundResource(R.drawable.bg_home_item_type);
                viewHolder.mItemTodoType.setTextColor(mContext.getResources().getColor(R.color.home_top_green));
                viewHolder.mItemTodoType.setText("时间充足");
            } else {

                // 即将超时蓝色背景
                viewHolder.mItemTodoType.setBackgroundResource(R.drawable.bg_home_item_new);
                viewHolder.mItemTodoType.setTextColor(Color.BLUE);
                viewHolder.mItemTodoType.setText("即将超时");
            }

            // 已完成TODO
        } else {
            Calendar complete = Calendar.getInstance();
            Calendar target = Calendar.getInstance();
            complete.setTime(new Date(data.get(i).getCompleteDate()));
            target.setTime(new Date(data.get(i).getDate()));
            if (complete.after(target)) {
                // 超时红色背景
                viewHolder.mItemTodoType.setBackgroundResource(R.drawable.bg_home_item_top);
                viewHolder.mItemTodoType.setTextColor(Color.RED);
                viewHolder.mItemTodoType.setText("超时完成");
            } else {
                // 绿色背景
                viewHolder.mItemTodoType.setBackgroundResource(R.drawable.bg_home_item_type);
                viewHolder.mItemTodoType.setTextColor(mContext.getResources().getColor(R.color.home_top_green));
                viewHolder.mItemTodoType.setText("按时完成");
            }
        }
        viewHolder.mView.setVisibility(i == data.size() - 1 ? View.GONE : View.VISIBLE);
        if (listener != null) {
            viewHolder.itemView.setOnClickListener(v -> listener.onItemClick(data.get(i), i));
        }

    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_todo_type)
        TextView mItemTodoType;
        @BindView(R.id.item_todo_targetTime)
        TextView mItemTodoTargetTime;
        @BindView(R.id.item_todo_title)
        TextView mItemTodoTitle;
        @BindView(R.id.item_todo_content)
        TextView mItemTodoContent;
        @BindView(R.id.item_todo_divide)
        View mView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(TodoBean bean, int position);
    }
}
