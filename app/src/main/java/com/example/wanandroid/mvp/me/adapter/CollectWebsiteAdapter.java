package com.example.wanandroid.mvp.me.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.CollectWebsiteBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author gaofengpeng
 * @date 2019/8/8
 * @description :
 */
public class CollectWebsiteAdapter extends RecyclerView.Adapter<CollectWebsiteAdapter.ViewHolder> {


    private List<CollectWebsiteBean> data;
    private Context mContext;
    private OnItemClickListener mListener;

    public CollectWebsiteAdapter(Context context) {
        this.mContext = context;
        data = new ArrayList<>();
    }

    /**
     * 设置新数据
     *
     * @param data 新数据
     */
    public void setData(List<CollectWebsiteBean> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyItemRangeChanged(0, data.size());
    }

    public void setOnItemClickListener(OnItemClickListener lisntener) {
        if (lisntener != null) {
            this.mListener = lisntener;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_collect_website, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        if (TextUtils.isEmpty(data.get(i).getName())) {
            viewHolder.mItemCollectText.setVisibility(View.GONE);
        } else {
            viewHolder.mItemCollectText.setVisibility(View.VISIBLE);
            viewHolder.mItemCollectText.setText(data.get(i).getName());
        }

        viewHolder.mItemCollectWeb.setText(data.get(i).getLink());

        viewHolder.mItemCollectText.setText(data.get(i).getName());
        if (TextUtils.isEmpty(data.get(i).getIcon())) {
            viewHolder.mItemCollectIcon.setVisibility(View.GONE);
        } else {
            viewHolder.mItemCollectIcon.setVisibility(View.VISIBLE);
            viewHolder.mItemCollectIcon.setImageResource(R.mipmap.ic_me);
        }
        if (mListener != null) {
            viewHolder.itemView.setOnClickListener(v -> mListener.onItemClick(i, v));
        }

    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_collect_icon)
        ImageView mItemCollectIcon;
        @BindView(R.id.item_collect_text)
        TextView mItemCollectText;
        @BindView(R.id.item_collect_web)
        TextView mItemCollectWeb;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        /**
         * RecyclerView的item的点击事件
         *
         * @param position 点击所在的position
         * @param view     点击的View
         */
        void onItemClick(int position, View view);
    }
}
