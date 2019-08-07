package com.example.wanandroid.mvp.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wanandroid.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author gaofengpeng
 * @date 2019/8/6
 * @description :
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<String> data;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public HistoryAdapter(Context context) {
        this.mContext = context;
        data = new ArrayList<>();
    }

    /**
     * 添加数据，添加在原数据之后
     * @param stringList String对象数据
     */
    public void addData(List<String> stringList) {
        int position = this.data.size();
        this.data.addAll(stringList);
        notifyItemRangeInserted(position, stringList.size());
    }

    /**
     * 设置数据，清空原数据后添加
     * @param stringList String对象数据
     */
    public void setData(List<String> stringList) {
        this.data.clear();
        this.data.addAll(stringList);
        notifyItemRangeChanged(0,stringList.size());
    }

    public void clear(){
        this.data.clear();
        notifyDataSetChanged();
    }

    /**
     * 设置item点击事件
     * @param listener 点击事件对象
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        if (listener != null) {
            this.mOnItemClickListener = listener;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_search_history, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mHomeSearchHistoryItemContent.setText(data.get(i));
        viewHolder.mHomeSearchHistoryItemDelete.setOnClickListener(v -> {
            this.data.remove(viewHolder.getAdapterPosition());
            notifyItemRemoved(viewHolder.getAdapterPosition());
        });
        if (i == data.size() - 1) {
            viewHolder.mHomeSearchHistoryItemDivide.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.mHomeSearchHistoryItemDivide.setVisibility(View.VISIBLE);
        }
        viewHolder.itemView.setOnClickListener(v -> {
            mOnItemClickListener.onItemClicked(viewHolder.getAdapterPosition(), data.get(viewHolder.getAdapterPosition()));
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_search_history_item_content)
        TextView mHomeSearchHistoryItemContent;
        @BindView(R.id.home_search_history_item_delete)
        ImageView mHomeSearchHistoryItemDelete;
        @BindView(R.id.home_search_history_item_divide)
        View mHomeSearchHistoryItemDivide;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public interface OnItemClickListener {
        /**
         * item的点击事件
         * @param position 点击位置
         * @param value item对应的数据
         */
        void onItemClicked(int position, String value);
    }
}
