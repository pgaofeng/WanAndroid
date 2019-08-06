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

    public HistoryAdapter(Context context) {
        this.mContext = context;
        data = new ArrayList<>();
    }

    public void addData(List<String> stringList) {
        int position = this.data.size();
        this.data.addAll(stringList);
        notifyItemRangeInserted(position, stringList.size());
    }

    public void setData(List<String> stringList) {
        this.data.clear();
        this.data.addAll(stringList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_search_history, null, false);
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
}
